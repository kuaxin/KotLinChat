package com.bw.kotlinchat.util

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.Toast

/**
 * Create by Rgx on 2019/4/10 18:44
 * Description:
 */



    fun Any.toast(context: Context, duration: Int = Toast.LENGTH_SHORT): Toast {

        return Toast.makeText(context, this.toString(), duration).apply { show() }
    }

