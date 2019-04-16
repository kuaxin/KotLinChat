package com.bw.kotlinchat.ui.fragment

import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.bw.kotlinchat.R
import com.bw.kotlinchat.adapter.ContactAdapter
import com.bw.kotlinchat.adapter.EMCContactAdapter
import com.bw.kotlinchat.contract.ContartContract
import com.bw.kotlinchat.presenter.ContartPresenter
import com.bw.kotlinchat.ui.activity.AddFriendActivity
import com.bw.kotlinchat.widget.SlideBar
import kotlinx.android.synthetic.main.fragment_contacts.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.toast
import com.hyphenate.chat.EMClient
import org.jetbrains.anko.startActivity


/**
 * Create by Rgx on 2019/4/12 20:41
 * Description:
 */
class ContactsFragment:BaseFragment(),ContartContract.View {


    val persenter = ContartPresenter(this);

    override fun contartDataSuccess() {
        swipeRefreshLayout!!.isRefreshing = false
        recyclerView.adapter?.notifyDataSetChanged()
    }

    override fun contartDataError() {
        swipeRefreshLayout!!.isRefreshing = false
        context?.toast(R.string.load_contacts_failed)

    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_contacts
    }

    override fun init() {
        super.init()

        tv_title.text = getString(R.string.contact)
        iv_right.visibility = View.VISIBLE
        iv_right.setOnClickListener {
            context?.startActivity<AddFriendActivity>()
        }



        swipeRefreshLayout.apply {
            setColorSchemeResources(R.color.qq_blue)
            isRefreshing = true
            setOnRefreshListener { persenter.getContartData() }
        }

        recyclerView.apply {

            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = ContactAdapter(context,persenter.contartListItems)

        }

        EMClient.getInstance().contactManager().setContactListener(object : EMCContactAdapter() {
            override fun onContactDeleted(username: String?) {
                persenter.getContartData()
            }
        })


        slideBar!!.onMoveSlideLister = object :SlideBar.OnMoveSlideLister{
            override fun getSlideIndex(firstLetter: String) {
                section.text = firstLetter
                section.visibility = View.VISIBLE
                context?.toast(persenter.contartListItems.size.toString())
                val position = getPosition(firstLetter)
                recyclerView.smoothScrollToPosition(position)

            }
            override fun onSlideFinish() {
                section.visibility = View.GONE
            }
        }

        persenter.getContartData()

    }

    private fun getPosition(firstLetter: String): Int {

        val binarySearch = persenter.contartListItems.binarySearch {
                it -> it.firstLetter.minus(firstLetter[0])
            Log.v("RGX",it.firstLetter.toString())
        }
        if(binarySearch < 0){
            return 0;
        }else {
            return binarySearch
        }

    }

}