package com.bw.kotlinchat

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.bw.kotlinchat.util.toast
import kotlin.reflect.KClass


/**
 * Create by Rgx on 2019/4/10 19:32
 * Description:
 */
abstract class BaseActivity: AppCompatActivity() {

    val progressDialog by lazy {
        ProgressDialog(this)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(setLayout())
        init()
    }

    abstract fun setLayout(): Int

    open fun init(){

    }

    fun showToast(msg:String){
        msg.toast(this)
    }


    protected var mhandler:Handler = Handler();
    fun startActivity1( clazz:Class<Any>,isFinish:Boolean){
        if(isFinish){
            startActivity(Intent(this,clazz));
            finish()
        }
        startActivity(Intent(this,clazz));
    }


    fun showProgressDialog(msg:String){
        progressDialog.setMessage(msg);
        progressDialog.show()
    }

    fun dismissProgressDialog(){
        progressDialog.dismiss()
    }
}