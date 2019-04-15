package com.bw.kotlinchat.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.bw.kotlinchat.R
import com.bw.kotlinchat.data.ContartListItem
import kotlinx.android.synthetic.main.list_item_contact.view.*

/**
 * Create by Rgx on 2019/4/15 11:31
 * Description:
 */
class ContractListItemView(context: Context?, attrs: AttributeSet? = null) : RelativeLayout(context, attrs) {
    fun bindView(contartListItem: ContartListItem) {
        if(contartListItem.showFirstLetter){
            tv_section.visibility = View.VISIBLE
            tv_section.text = contartListItem.firstLetter.toString()
        }else tv_section.visibility = View.GONE

        tv_username.text = contartListItem.username

    }

    init {
        View.inflate(context, R.layout.list_item_contact,this)
    }
}