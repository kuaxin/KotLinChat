package com.bw.kotlinchat.ui.activity

import android.support.v7.widget.LinearLayoutManager
import android.view.KeyEvent
import android.widget.TextView
import com.bw.kotlinchat.R
import com.bw.kotlinchat.adapter.AddFriendAdapter
import com.bw.kotlinchat.contract.AddFriendContract
import com.bw.kotlinchat.presenter.AddFirendPersenter
import com.bw.kotlinchat.util.toast
import kotlinx.android.synthetic.main.activity_add_friend.*
import kotlinx.android.synthetic.main.header.*
import kotlinx.android.synthetic.main.view_add_friend_item.*
import org.jetbrains.anko.toast

/**
 * Create by Rgx on 2019/4/16 11:17
 * Description:
 */
class AddFriendActivity:BaseActivity(),AddFriendContract.View {

    val addFriendPersenter = AddFirendPersenter(this);


    override fun onSearchSuccess() {
        dismissProgressDialog()
        toast("搜索完成")
        searchRecyclerView.adapter!!.notifyDataSetChanged()
    }

    override fun onSearchFail() {
        dismissProgressDialog()
        toast("搜索失败")
    }

    override fun setLayout(): Int {
        return R.layout.activity_add_friend
    }

    override fun init() {

        tv_title.text = "添加好友"

        searchRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = AddFriendAdapter(context,addFriendPersenter.addFriendItems)
        }

        iv_search.setOnClickListener {
            search()
        }
        et_username.setOnEditorActionListener { v, actionId, event ->
            search()
            true
        }
    }

    private fun search() {
        hideSoftKeyBorad()
        showProgressDialog("正在搜索好友")
        addFriendPersenter.search(et_username.text.toString().trim())
    }


}