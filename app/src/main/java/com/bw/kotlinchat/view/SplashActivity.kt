package com.bw.kotlinchat.view

import android.animation.ObjectAnimator
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bw.kotlinchat.BaseActivity
import com.bw.kotlinchat.LoginActivity
import com.bw.kotlinchat.MainActivity
import com.bw.kotlinchat.R
import com.bw.kotlinchat.presenter.SplashPresenterImpl
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class SplashActivity : BaseActivity(),SplashView {
    override fun setLayout(): Int {
        return R.layout.activity_splash
    }

    lateinit var iv: ImageView;
    lateinit var splashPresenterImpl: SplashPresenterImpl;



    override fun init() {
        iv = findViewById(R.id.iv)
        splashPresenterImpl = SplashPresenterImpl(this);
        splashPresenterImpl.isLogined()
    }

    override fun checkLogin(isLogined: Boolean) {
        if(isLogined){


//            var intent:Intent = Intent(this,MainActivity::class.java)
//            startActivity(intent)
            startActivity1(MainActivity().javaClass,true);
        }else{
            toast("你好")
            var objectanimator: ObjectAnimator = ObjectAnimator.ofFloat(iv,"alpha",0f,1f);
            objectanimator.setDuration(2000).start();
            mhandler.postDelayed(Runnable {
                run {
                    startActivity1(LoginActivity().javaClass,true);
             }
            },2000);
        }
    }

}
