package com.lyj.githubsearchapp.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lyj.githubsearchapp.R


class UserListAdapter : RecyclerView.Adapter<UserListAdapter.GithubUserListViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubUserListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user,parent,false)
        return GithubUserListViewHolder(view)
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: GithubUserListViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    inner class GithubUserListViewHolder(view : View) : RecyclerView.ViewHolder(view){

    }
}