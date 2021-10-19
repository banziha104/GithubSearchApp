package com.lyj.githubsearchapp.presentation.adapter

import android.util.Log
import androidx.recyclerview.widget.DiffUtil
import com.lyj.githubsearchapp.domain.model.GithubUserModel
import com.lyj.githubsearchapp.presentation.activity.GithubModelWithFavorite
import com.lyj.githubsearchapp.presentation.activity.InitialSound
import io.reactivex.rxjava3.subjects.BehaviorSubject

/**
 * UserList에서 사용될 items 를 관리
 * UserListAdapter 에 변경된 데이터를 List 형태로 전달
 */
class UserListAdapterViewModel(
    val onItemClickObserver: UserListAdapter.OnUserListAdapterItemClickedObserver
) : UserListAdapterDataChanger {

    /**
     * 데이터 변경을 발행하는 Subject
     */
    val dataEventDriver: BehaviorSubject<List<UserListDataModel>> =
        BehaviorSubject.createDefault(listOf())

    /**
     * ViewModel 에서 관리하는 데이터 셋
     * 초성을 key로, 즐겨찾기가 표시된 모델 리스트를 value로 가진 Map 형태이며,
     * View 에 전달 시, List 형태로 가공
     */
    private val items: MutableMap<InitialSound, List<GithubModelWithFavorite>> = mutableMapOf()

    /**
     * 이벤트를 발행하는 메소드
     */
    private fun submitEvent() {
        val list = items
            .entries
            .sortedBy { it.key } // 초성을 기준으로 정렬
            .map { entry ->
                entry
                    .value
                    .sortedBy { (model, _) -> model.userName } // 모델의 userName을 기준으로 정렬
                    .mapIndexed { index, (model, isFavorite) ->
                        if (index == 0) { // 현재 데이터가 첫 번째인 경우 FirstData로 생성, 아닌 경우 Data로 생성
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

/**
 * ViewModel 관련 데이터 조작을 추상화한 인터페이스
 */
interface UserListAdapterDataChanger {
    fun addData(data: Map<InitialSound, List<GithubModelWithFavorite>>) // 데이터가 추가된 경우
    fun changeData(data: Map<InitialSound, List<GithubModelWithFavorite>>) // 전체 데이터 변경이 있는 경우
    fun notifyRemoveItem(model: UserListDataModel, position: Int) // 아이템 한개만 삭제되는 경우
    fun notifyChangeItem(model: UserListDataModel, position: Int) // 아이템 한개만 변경된 경우
}

/**
 * UserList 에서 사용하는 모델 클래스
 */
sealed interface UserListDataModel : GithubUserModel {
    var isFavorite: Boolean
    val initialSound: Char

    /**
     * 같은 초성으로 이루어진 그룹에서 첫번째인 경우의 모델
     *
     * @param githubUserModel GithubuserModel 구현을 위임 하기 위해 전달받는 원본 모델 객체
     */
    class FirstData(
        githubUserModel: GithubUserModel,
        override var isFavorite: Boolean,
        override val initialSound: Char
    ) : UserListDataModel, GithubUserModel by githubUserModel

    /**
     * 같은 초성으로 이루어진 그룹에서 첫번째가 아닌 경우의 모델
     *
     *  @param githubUserModel GithubuserModel 구현을 위임 하기 위해 전달받는 원본 모델 객체
     */
    class Data(
        githubUserModel: GithubUserModel,
        override var isFavorite: Boolean,
        override val initialSound: Char
    ) : UserListDataModel, GithubUserModel by githubUserModel
}