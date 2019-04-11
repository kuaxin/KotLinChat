package com.bw.kotlinchat.presenter

import com.bw.kotlinchat.adapter.EMCallBackAdapter
import com.bw.kotlinchat.contract.LoginContract
import com.bw.kotlinchat.extentions.checkPwd
import com.bw.kotlinchat.extentions.checkUserName
import com.hyphenate.EMCallBack
import com.hyphenate.chat.EMClient

/**
 * Create by Rgx on 2019/4/11 13:57
 * Description:
 */
class LoginPresenter(val view:LoginContract.View):LoginContract.LoginPresenter {
    override fun login(userName: String, pwd: String) {
        if(userName.checkUserName()){
            if(pwd.checkPwd()){
                view.loginStaus()
                EMCLogin(userName,pwd);
            }else {
                view.pwdError()
            }
        }else{
            view.userNameError()
        }
    }

    private fun EMCLogin(userName: String, pwd: String) {
        EMClient.getInstance().login(userName,pwd,object: EMCallBackAdapter() {
            override fun onSuccess() {
                EMClient.getInstance().groupManager().loadAllGroups();
                EMClient.getInstance().chatManager().loadAllConversations();
                uiThread { view.loginSuccess() }
            }

            override fun onError(p0: Int, p1: String?) {
                uiThread { view.loginFail() }
            }

        })
    }
}