package com.bw.kotlinchat.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.bw.kotlinchat.R
import com.bw.kotlinchat.data.ContartListItem
import com.bw.kotlinchat.ui.activity.ChatActivity
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
class ContactAdapter(val context:Context,var contartListItems: MutableList<ContartListItem>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        return MyViewHolder(ContractListItemView(context))
    }

    override fun getItemCount(): Int  {
        return contartListItems.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val contractListItemView = holder.itemView as ContractListItemView
        contractListItemView.bindView(contartListItems[position]);
        val username = contartListItems[position].username
        contractListItemView.setOnClickListener{context?.startActivity<ChatActivity>("username" to username)}
        contractListItemView.setOnLongClickListener {
            val message = String.format(context.getString(R.string.delete_friend_message), username)
            AlertDialog.Builder(context).setTitle(R.string.delete_friend_title)
                .setMessage(message)
                .setNegativeButton(R.string.cancel,null)
                .setPositiveButton(R.string.confirm, DialogInterface.OnClickListener { dialog, which ->
                    deleteFriend(username,position);
                }).show()

            true
        }
    }

    private fun deleteFriend(username: String, position: Int) {

        doAsync {
            try {
                EMClient.getInstance().contactManager().deleteContact(username);
                context.runOnUiThread { toast("删除好友成功") }
            } catch (e: Exception) {
                context.runOnUiThread { toast("删除失败") }
            }
        }

    }




    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

}