package com.bw.kotlinchat.presenter

import com.bw.kotlinchat.view.SplashView
import com.hyphenate.chat.EMClient

/**
 * Create by Rgx on 2019/4/10 16:52
 * Description:
 */
class SplashPresenterImpl(val splashView:SplashView) : SplashPersenter {




    override fun isLogined() {
        if(EMClient.getInstance().isLoggedInBefore && EMClient.getInstance().isConnected){
            splashView.checkLogin(true)
        }else{
            splashView.checkLogin(false)
        }
    }
}