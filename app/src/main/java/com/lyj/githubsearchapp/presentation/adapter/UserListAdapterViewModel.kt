package com.lyj.githubsearchapp.presentation.adapter

import android.util.Log
import androidx.recyclerview.widget.DiffUtil
import com.lyj.githubsearchapp.domain.model.GithubUserModel
import com.lyj.githubsearchapp.presentation.activity.GithubModelWithFavorite
import com.lyj.githubsearchapp.presentation.activity.InitialSound
import io.reactivex.rxjava3.subjects.BehaviorSubject

class UserListAdapterViewModel(
    val onItemClickObserver: UserListAdapter.OnUserListAdapterItemClickedObserver
) : UserListAdapterDataChanger {

    val dataEventDriver: BehaviorSubject<List<UserListDataModel>> = BehaviorSubject.createDefault(listOf())

    private val items: MutableMap<InitialSound, List<GithubModelWithFavorite>> = mutableMapOf()

    private fun submitEvent() {
        val list = items
            .entries
            .sortedBy { it.key }
            .map { entry ->
                entry
                    .value
                    .sortedBy { (model, _) -> model.userName }
                    .mapIndexed { index, (model, isFavorite) ->
                        if (index == 0) {
                            UserListDataModel.FirstData(model, isFavorite, entry.key)
                        } else {
                            UserListDataModel.Data(model, isFavorite, entry.key)
                        }
                    }
            }
            .flatten()
        dataEventDriver.onNext(list)
    }

    override fun addData(data: Map<InitialSound, List<GithubModelWithFavorite>>) {
        items += data
        submitEvent()
    }

    override fun changeData(data: Map<InitialSound, List<GithubModelWithFavorite>>) {
        items.clear()
        items += data
        submitEvent()
    }

    override fun notifyRemoveItem(model: UserListDataModel, position: Int) {
        val list = items[model.initialSound]?.toMutableList() ?: return
        val index = list.indexOfFirst { (userModel) -> userModel.userName == model.userName }.also {
            if (it == -1) {
                return
            }
        }
        list.removeAt(index)
        items[model.initialSound] = list
        submitEvent()
    }

    override fun notifyChangeItem(model: UserListDataModel, position: Int) {
        val list = items[model.initialSound]?.toMutableList() ?: return
        val index = list.indexOfFirst { (userModel) -> userModel.userName == model.userName }.also {
            if (it == -1) {
                return
            }
        }

        list[index] = list[index].let { (model, isFavorite) ->
            model to !isFavorite
        }
        items[model.initialSound] = list
        submitEvent()
    }
}

interface UserListAdapterDataChanger {
    fun addData(data: Map<InitialSound, List<GithubModelWithFavorite>>)
    fun changeData(data: Map<InitialSound, List<GithubModelWithFavorite>>)
    fun notifyRemoveItem(model: UserListDataModel, position: Int)
    fun notifyChangeItem(model: UserListDataModel, position: Int)
}

sealed interface UserListDataModel : GithubUserModel {
    var isFavorite: Boolean
    val initialSound: Char

    class FirstData(
        githubUserModel: GithubUserModel,
        override var isFavorite: Boolean,
        override val initialSound: Char
    ) : UserListDataModel, GithubUserModel by githubUserModel

    class Data(
        githubUserModel: GithubUserModel,
        override var isFavorite: Boolean,
        override val initialSound: Char
    ) : UserListDataModel, GithubUserModel by githubUserModel
}