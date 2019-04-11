package com.bw.kotlinchat.util

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.Toast

/**
 * Create by Rgx on 2019/4/10 18:44
 * Description:
 */
object ToastUtil {

        private var stoast: Toast? = null
            private val handler = Handler(Looper.getMainLooper())

            fun showToast(context: Context, msg: String) {
                if (stoast == null) {
                    stoast = Toast.makeText(context.applicationContext, msg, Toast.LENGTH_SHORT)
                }
                stoast!!.setText(msg)
                if (Looper.myLooper() == Looper.getMainLooper()) {
                    stoast!!.show()
                } else {
                    handler.post { stoast!!.show() }
                }
            }
}