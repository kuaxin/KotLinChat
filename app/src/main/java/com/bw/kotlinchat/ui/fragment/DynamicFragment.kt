package com.bw.kotlinchat.ui.fragment

import android.content.Context
import com.bw.kotlinchat.R
import com.bw.kotlinchat.ui.activity.LoginActivity
import com.hyphenate.chat.EMClient
import kotlinx.android.synthetic.main.fragment_dynamic.*
import kotlinx.android.synthetic.main.header.*
import com.hyphenate.EMCallBack
import org.jetbrains.anko.runOnUiThread
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast


/**
 * Create by Rgx on 2019/4/12 20:41
 * Description:
 */
class DynamicFragment:BaseFragment() {
    override fun getLayoutId(): Int {
        return R.layout.fragment_dynamic
    }

    override fun init() {
        super.init()
        tv_title.setText(R.string.dynamic);
        var logoutString = String.format(getString(R.string.logout),EMClient.getInstance().currentUser);
        logout.text = logoutString;


        logout.setOnClickListener{logout()}

    }
    fun logout(){

        EMClient.getInstance().logout(true, object : EMCallBack {

            override fun onSuccess() {
                context?.runOnUiThread {
                    toast("退出成功")
                    context?.startActivity<LoginActivity>()
                    activity?.finish()
                }
            }

            override fun onProgress(progress: Int, status: String) {
                // TODO Auto-generated method stub

            }

            override fun onError(code: Int, message: String) {
                // TODO Auto-generated method stub
                context?.runOnUiThread {
                    toast("网络异常，退出登录失败")
                }
            }
        })

    }

}


