package com.bw.kotlinchat.contract

import android.os.Handler
import android.os.Looper

/**
 * Create by Rgx on 2019/4/11 10:43
 * Description:
 */
interface BasePresenter {

    companion object {
        val handler by lazy {
            Handler(Looper.getMainLooper())
        }
    }

    fun uiThread(f:()->Unit){
        handler.post { f() }
    }

}