package com.bw.kotlinchat.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.bw.kotlinchat.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun setLayout(): Int {
        return R.layout.activity_main
    }

    override fun init() {
        super.init()
        bottomBar.setOnTabSelectListener { tabId ->
            val beginTransaction = supportFragmentManager.beginTransaction();
//            beginTransaction.replace(R.id.fragment_frame,)
        }

    }

}
