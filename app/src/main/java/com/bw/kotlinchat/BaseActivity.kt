package com.bw.kotlinchat

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.bw.kotlinchat.util.ToastUtil
import kotlin.reflect.KClass


/**
 * Create by Rgx on 2019/4/10 19:32
 * Description:
 */
abstract class BaseActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(setLayout())
        init()
    }

    abstract fun setLayout(): Int

    open fun init(){

    }

    fun showToast(msg:String){
        ToastUtil.showToast(this,msg);
    }


    protected var mhandler:Handler = Handler();
    fun startActivity1( clazz:Class<Any>,isFinish:Boolean){
        if(isFinish){
            startActivity(Intent(this,clazz));
            finish()
        }
        startActivity(Intent(this,clazz));
    }


}