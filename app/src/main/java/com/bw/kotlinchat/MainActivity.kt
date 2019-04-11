package com.bw.kotlinchat

import android.animation.ObjectAnimator
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bw.kotlinchat.presenter.SplashPresenterImpl
import com.bw.kotlinchat.view.SplashView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

}
