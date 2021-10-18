package com.lyj.githubsearchapp.presentation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jakewharton.rxbinding4.view.clicks
import com.lyj.githubsearchapp.R
import com.lyj.githubsearchapp.domain.model.GithubUserModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class UserListAdapter(
    private val viewModel: UserListAdapterViewModel
) : RecyclerView.Adapter<UserListAdapter.UserListViewHolder>() {

    private var items: List<UserListDataModel> = listOf()

    init {
        viewModel.dataEventDriver
            .observeOn(Schedulers.computation())
            .map { newItems ->
                val diff = UserListDataDiffUtils(items, newItems)
                items = newItems
                DiffUtil.calculateDiff(diff)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                result.dispatchUpdatesTo(this)
            }, {
                it.printStackTrace()
            })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserListViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        val item = items[position]
        holder.apply {
            if (item is UserListDataModel.FirstData) {
                txtInitialSound.text = item.initialSound.toString()
                txtInitialSound.visibility = View.VISIBLE
            } else {
                txtInitialSound.visibility = View.GONE
            }

            txtName.text = item.userName

            Glide
                .with(holder.itemView)
                .load(item.avatarUrl)
                .into(imgAvatar)

            Glide
                .with(holder.itemView)
                .load(if (item.isFavorite) R.drawable.ic_star_normal else R.drawable.ic_star_inverted)
                .into(btnFavorite.apply {
                    tag = if (item.isFavorite) R.drawable.ic_star_normal else R.drawable.ic_star_inverted // 테스트용 태그
                })
        }
    }

    inner class UserListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtName: TextView = view.findViewById<TextView>(R.id.userItemTxtName)
        val txtInitialSound: TextView = view.findViewById<TextView>(R.id.userItemTxtInitialSound)
        val btnFavorite: ImageButton = view.findViewById<ImageButton>(R.id.userItemBtnFavorite)
        val imgAvatar: ImageView = view.findViewById<ImageView>(R.id.userItemImgAvatar)

        init {
            viewModel.onItemClickObserver.func(
                btnFavorite
                    .clicks()
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .map { adapterPosition to items[adapterPosition] }
            )
        }
    }

    @JvmInline
    value class OnUserListAdapterItemClickedObserver(val func: (Observable<Pair<Int, UserListDataModel>>) -> Unit)
}


class UserListDataDiffUtils(
    private val oldItems: List<GithubUserModel>,
    private val newItems: List<GithubUserModel>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldItems.size

    override fun getNewListSize(): Int = newItems.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldItems[oldItemPosition].userName == newItems[newItemPosition].userName


    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldItems[oldItemPosition] === newItems[newItemPosition]
}