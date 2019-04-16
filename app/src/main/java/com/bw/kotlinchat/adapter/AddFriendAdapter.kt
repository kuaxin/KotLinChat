package com.bw.kotlinchat.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.bw.kotlinchat.R
import com.bw.kotlinchat.data.AddFriendListItem
import com.bw.kotlinchat.data.ContartListItem
import com.bw.kotlinchat.presenter.AddFirendPersenter
import com.bw.kotlinchat.ui.activity.ChatActivity
import com.bw.kotlinchat.widget.AddFriendListItemView
import com.bw.kotlinchat.widget.ContractListItemView
import com.hyphenate.chat.EMClient
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.runOnUiThread
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * Create by Rgx on 2019/4/15 11:39
 * Description:
 */
class AddFriendAdapter(val context:Context,val addFriendItems:MutableList<AddFriendListItem>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        return MyViewHolder(AddFriendListItemView(context))
    }

    override fun getItemCount(): Int  {
        return addFriendItems.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val addFriendListItemView = holder?.itemView as AddFriendListItemView
        addFriendListItemView.bindView(addFriendItems[position]);
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

}