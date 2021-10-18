package com.lyj.githubsearchapp.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jakewharton.rxbinding4.view.clicks
import com.lyj.githubsearchapp.R
import com.lyj.githubsearchapp.domain.model.GithubUserModel
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit

class UserListAdapter(
    private val onItemClickObserver: OnUserListAdapterItemClickedObserver
) : RecyclerView.Adapter<UserListAdapter.UserListViewHolder>(),
    UserListAdapterController {

    private val items: MutableList<UserListData> by lazy {
        mutableListOf()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserListViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        val item = items[position]
        holder.apply {
            if (item is UserListData.GithubUserDataWithInitialSound){
                txtInitialSound.text = item.initialSound.toString()
                txtInitialSound.visibility = View.VISIBLE
            }else{
                txtInitialSound.visibility = View.GONE
            }

            txtName.text = item.userName

            Glide
                .with(holder.itemView)
                .load(item.avatarUrl)
                .into(imgAvatar)
        }
    }

    override fun addData(data: List<UserListData>) {
        items.addAll(data)
        notifyItemRangeChanged(items.size, data.size)
    }

    override fun changeData(data: List<UserListData>) {
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }

    override fun notifyRemoveItem(position: Int) = notifyItemRemoved(position)
    override fun notifyChangeItem(position: Int) = notifyItemChanged(position)


    inner class UserListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtName: TextView = view.findViewById<TextView>(R.id.userItemTxtName)
        val txtInitialSound: TextView = view.findViewById<TextView>(R.id.userItemTxtInitialSound)
        val btnFavorite: ImageButton = view.findViewById<ImageButton>(R.id.userItemBtnFavorite)
        val imgAvatar: ImageView = view.findViewById<ImageView>(R.id.userItemImgAvatar)

        init {
            onItemClickObserver.func(
                btnFavorite
                    .clicks()
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .map { adapterPosition to items[adapterPosition] }
            )
        }
    }

    @JvmInline
    value class OnUserListAdapterItemClickedObserver(val func: (Observable<Pair<Int, UserListData>>) -> Unit)
}

interface UserListAdapterController {
    fun addData(data: List<UserListData>)
    fun changeData(data: List<UserListData>)
    fun notifyRemoveItem(position: Int)
    fun notifyChangeItem(position: Int)
}

sealed interface UserListData : GithubUserModel {

    val isFavorite : Boolean

    class GithubUserData(
        githubUserModel: GithubUserModel,
        override val isFavorite : Boolean
    ) : GithubUserModel by githubUserModel, UserListData

    class GithubUserDataWithInitialSound(
        githubUserModel: GithubUserModel,
        override val isFavorite : Boolean,
        val initialSound: Char
    ) : GithubUserModel by githubUserModel, UserListData
}