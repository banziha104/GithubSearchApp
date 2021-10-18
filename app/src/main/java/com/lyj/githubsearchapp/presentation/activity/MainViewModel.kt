package com.lyj.githubsearchapp.presentation.activity

import androidx.annotation.StringRes
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lyj.githubsearchapp.R
import com.lyj.githubsearchapp.common.utils.KoreanLangagueUtils
import com.lyj.githubsearchapp.domain.model.GithubUserModel
import com.lyj.githubsearchapp.domain.usecase.local.InsertOrDeleteUserModelUseCase
import com.lyj.githubsearchapp.domain.usecase.local.ObserveLocalUserListUseCase
import com.lyj.githubsearchapp.domain.usecase.remote.GetRemoteUserListUseCase
import com.lyj.githubsearchapp.presentation.adapter.UserListData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.PublishSubject
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val getRemoteUserListUseCase: GetRemoteUserListUseCase,
    val insertOrDeleteUserModelUseCase: InsertOrDeleteUserModelUseCase,
    val observeLocalUserListUseCase: ObserveLocalUserListUseCase
) : ViewModel() {

    companion object {
        val DEFAULT_TAB = MainTabType.API
    }

    val currentTabType: MutableLiveData<MainTabType> by lazy {
        MutableLiveData<MainTabType>(DEFAULT_TAB)
    }


    fun getData(
        targetData: List<GithubUserModel>,
        localData: List<GithubUserModel>
    ): List<UserListData> {
        val a = splitInitializeSoundData(targetData)
            .groupBy { (initialSound, _) -> initialSound }
            .entries
            .sortedBy { it.key }
            .map { entry ->
                entry
                    .value
                    .sortedBy { list -> list.second.userName }
                    .mapIndexed { index, (initialSound, model) ->
                        val isFavorite = currentTabType.value!!.checkFavorite.func(model,localData)
                        if (index == 0) {
                            UserListData.GithubUserDataWithInitialSound(model, isFavorite, initialSound)
                        } else {
                            UserListData.GithubUserData(model, isFavorite)
                        }
                    }
            }
            .flatten()
        return a
    }

    private fun splitInitializeSoundData(list: List<GithubUserModel>): List<Pair<Char, GithubUserModel>> =
        list
            .filter { it.userName != null }
            .map {
                val (initialSound, _) = KoreanLangagueUtils.splitInitialSound(it.userName!!)
                initialSound to it
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