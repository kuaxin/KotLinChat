package com.bw.kotlinchat.presenter

import com.bw.kotlinchat.view.SplashView
import com.hyphenate.chat.EMClient

/**
 * Create by Rgx on 2019/4/10 16:52
 * Description:
 */
class SplashPresenterImpl : SplashPersenter {

    private lateinit var splashView:SplashView;

    constructor(splashView: SplashView) {
        this.splashView = splashView
    }


    override fun isLogined() {
        if(EMClient.getInstance().isLoggedInBefore && EMClient.getInstance().isConnected){
            splashView.checkLogin(true)
        }else{
            splashView.checkLogin(false)
        }
    }
}