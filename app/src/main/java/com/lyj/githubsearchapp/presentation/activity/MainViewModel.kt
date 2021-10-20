package com.lyj.githubsearchapp.presentation.activity

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import com.lyj.githubsearchapp.R
import com.lyj.githubsearchapp.common.utils.KoreanLanguageUtils
import com.lyj.githubsearchapp.domain.model.GithubUserModel
import com.lyj.githubsearchapp.domain.usecase.local.FindLocalDataByUserNameUseCase
import com.lyj.githubsearchapp.domain.usecase.local.GetLocalUserListUseCase
import com.lyj.githubsearchapp.domain.usecase.local.InsertOrDeleteUserModelUseCase
import com.lyj.githubsearchapp.domain.usecase.remote.GetRemoteUserListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

/**
 * GithubModel 과 즐겨찾기를 포함하는 타입
 */
typealias IsFavorite = Boolean
typealias InitialSound = Char

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getRemoteUserListUseCase: GetRemoteUserListUseCase,
    private val getLocalUserListUseCase: GetLocalUserListUseCase,
    private val findLocalDataByUserNameUseCase: FindLocalDataByUserNameUseCase,
    val insertOrDeleteUserModelUseCase: InsertOrDeleteUserModelUseCase
) : ViewModel() {

    companion object {
        val DEFAULT_TAB = MainTabType.API
    }

    /**
     * 가장 마지막에 클릭된 탭
     */
    var latestTabType: MainTabType = DEFAULT_TAB

    /**
     * 가장 마지막에 적용된 paging index
     */
    var latestPaging : Int = 1

    /**
     * 가장 마지막에 검색한 데이터
     */
    var latestSearchKeyword : String? = ""

    /**
     * [tabType]과 [searchKeyword] 를 기준으로 List에서 사용할 데이터를 요청하는 메소드
     *
     * @param tabType 가장 마지막에 클릭된 탭(검색 버튼 클릭) 또는 지금 클릭된 탭(TabLayout 클릭)
     * @param searchKeyword 검색어
     * @return 초성에 따라 그룹화된 맵을 반환(key : 초성, value : 즐겨찾기가 표시된 model [GithubModelWithFavorite]
     */
    fun requestGithubData(
        tabType: MainTabType,
        searchKeyword: String? = null,
        page: Int? = null
    ): Single<Map<InitialSound, List<GithubModelWithFavorite>>> {

        return when (tabType) {
            MainTabType.API -> {
                if (searchKeyword == null || searchKeyword.isBlank()) { // API 일때, 검색어가 정확하지 않은 경우 빈 Map 객체 반환
                    return Single.just(mapOf())
                }
                Single.zip(
                    getRemoteUserListUseCase.execute(searchKeyword, page),
                    getLocalUserListUseCase.execute()
                ) { remoteData, localData ->
                    matchFavorite(
                        remoteData,
                        localData,
                        MainTabType.API
                    )
                }
            }
            MainTabType.LOCAL -> {
                if (searchKeyword != null && searchKeyword.isNotBlank()) { // Local 일때, 검색어가 있는 경우 해당 검색어를 포함하는 데이터를 반환
                    findLocalDataByUserNameUseCase
                        .execute(searchKeyword)
                } else {
                    getLocalUserListUseCase.execute() // Local 일때, 검색어가 비어있는 경우, 전체 데이터 반환
                }.map {
                    matchFavorite(it, it, MainTabType.LOCAL)
                }
            }
        }
    }

    /**
     * 즐겨 찾기 여부를 표시하는 메소드
     *
     * @param targetData 비교 대상 리스트
     * @param localData 즐겨찾기 리스트
     * @return 초성에 따라 그룹화된 맵을 반환(key : 초성, value : 즐겨찾기가 표시된 model [GithubModelWithFavorite]
     */
    private fun matchFavorite(
        targetData: List<GithubUserModel>,
        localData: List<GithubUserModel>,
        tabType: MainTabType
    ): Map<InitialSound, List<GithubModelWithFavorite>> {
        return splitInitializeSoundData(targetData)
            .mapValues { entry ->
                entry.value.map {
                    GithubModelWithFavorite(it,tabType.checkFavorite.func(it, localData))
                }
            }
    }

    /**
     * 첫 글자를 통해 초성을 얻고, 해당 초성으로 그룹화하는 메소드
     *
     * @see [KoreanLanguageUtils.splitInitialSound]
     * @param list 초성으로 분해할 모델 리스트
     * @return 초성으로 그룹화된 Map
     */
    private fun splitInitializeSoundData(list: List<GithubUserModel>): Map<InitialSound, List<GithubUserModel>> =
        list
            .filter { it.userName != null }
            .groupBy {
                val (initialSound, _) = KoreanLanguageUtils.splitInitialSound(it.userName!!)
                initialSound
            }
}

/**
 * GithubUserModel 과 즐겨찾기 여부를 결합한 데이터 클래스
 */
data class GithubModelWithFavorite(
    private val model : GithubUserModel,
    var isFavorite: IsFavorite
) : GithubUserModel by model

/**
 * TabLayout 의 타입
 *
 * @param title String 리소스
 * @param checkFavorite [CheckFavorite] 현재 탭에 따라 즐겨찾기를 구현한 로직 (API 의 경우 Local 의 존재여부, Local은 즐겨찾기만 저장됨으로 모두 true)
 * @property API api 탭
 * @property LOCAL local 탭
 */
enum class MainTabType(
    @StringRes val title: Int,
    val checkFavorite: CheckFavorite
) {
    API(
        R.string.main_api_tab_name,
        CheckFavorite { targetData, localData ->
            targetData.userName in localData.mapNotNull { it.userName }
        }
    ),
    LOCAL(
        R.string.main_local_tab_name,
        CheckFavorite { _, _ ->
            true
        }
    )
}

/**
 * 즐겨찾기 로직을 명시한 value class
 */
@JvmInline
value class CheckFavorite(
    /**
     * @param targetData : 비교 대상
     * @param localData : 저장된 로컬 데이터 리스트
     * @return 즐겨찾기 여부
     */
    val func: (targetData: GithubUserModel, localData: List<GithubUserModel>) -> IsFavorite
)