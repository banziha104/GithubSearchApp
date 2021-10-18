package com.lyj.githubsearchapp.presentation.activity

import androidx.annotation.StringRes
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lyj.githubsearchapp.R
import com.lyj.githubsearchapp.common.utils.KoreanLangagueUtils
import com.lyj.githubsearchapp.domain.model.GithubUserModel
import com.lyj.githubsearchapp.domain.usecase.local.InsertOrDeleteUserModelUseCase
import com.lyj.githubsearchapp.domain.usecase.local.GetLocalUserListUseCase
import com.lyj.githubsearchapp.domain.usecase.remote.GetRemoteUserListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

typealias IsFavorite = Boolean
typealias InitialSound = Char
typealias GithubModelWithFavorite = Pair<GithubUserModel, IsFavorite>

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getRemoteUserListUseCase: GetRemoteUserListUseCase,
    private val getLocalUserListUseCase: GetLocalUserListUseCase,
    val insertOrDeleteUserModelUseCase: InsertOrDeleteUserModelUseCase
) : ViewModel() {

    companion object {
        val DEFAULT_TAB = MainTabType.API
    }

    var latestTabType: MainTabType = DEFAULT_TAB

    fun requestGithubData(
        tabType: MainTabType,
        searchKeyword: String
    ): Single<Map<InitialSound, List<GithubModelWithFavorite>>> {
        return when (tabType) {
            MainTabType.API -> {
                Single.zip(
                    getRemoteUserListUseCase.execute(searchKeyword),
                    getLocalUserListUseCase.execute()
                ) { remoteData, localData ->
                    getUserListAdapterData(
                        remoteData,
                        localData,
                        MainTabType.API
                    )
                }
            }
            MainTabType.LOCAL -> {
                getLocalUserListUseCase.execute().map {
                    getUserListAdapterData(it, it, MainTabType.LOCAL)
                }
            }
        }
    }

    private fun getUserListAdapterData(
        targetData: List<GithubUserModel>,
        localData: List<GithubUserModel>,
        tabType: MainTabType
    ): Map<InitialSound, List<GithubModelWithFavorite>> {
        return splitInitializeSoundData(targetData)
            .mapValues { entry ->
                entry.value.map {
                    it to tabType.checkFavorite.func(it, localData)
                }
            }
    }

    private fun splitInitializeSoundData(list: List<GithubUserModel>): Map<InitialSound, List<GithubUserModel>> =
        list
            .filter { it.userName != null }
            .groupBy {
                val (initialSound, _) = KoreanLangagueUtils.splitInitialSound(it.userName!!)
                initialSound
            }
}

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

@JvmInline
value class CheckFavorite(val func: (targetData: GithubUserModel, localData: List<GithubUserModel>) -> Boolean)