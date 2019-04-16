package com.bw.kotlinchat.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.bw.kotlinchat.R
import com.bw.kotlinchat.data.AddFriendListItem
import kotlinx.android.synthetic.main.view_add_friend_item.view.*

/**
 * Create by Rgx on 2019/4/15 11:31
 * Description:
 */
class AddFriendListItemView(context: Context?, attrs: AttributeSet? = null) : RelativeLayout(context, attrs) {
    fun bindView(addFriendListItem: AddFriendListItem) {
        userName.text = addFriendListItem.username
        timestamp.text = addFriendListItem.timestart
    }

    init {
        View.inflate(context, R.layout.view_add_friend_item,this)
    }
}