package com.bw.kotlinchat.ui.activity

import android.content.pm.PackageManager
import android.graphics.Color
import android.support.v4.app.ActivityCompat
import android.text.TextUtils
import com.bw.kotlinchat.R
import com.bw.kotlinchat.contract.LoginContract
import com.bw.kotlinchat.presenter.LoginPresenter
import kotlinx.android.synthetic.main.activity_login.*
import master.flame.danmaku.controller.DrawHandler
import master.flame.danmaku.danmaku.model.BaseDanmaku
import master.flame.danmaku.danmaku.model.DanmakuTimer
import master.flame.danmaku.danmaku.model.IDanmakus
import master.flame.danmaku.danmaku.model.android.DanmakuContext
import master.flame.danmaku.danmaku.model.android.Danmakus
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * Create by Rgx on 2019/4/11 10:37
 * Description:
 */
class LoginActivity: BaseActivity(),LoginContract.View {
    var showDanmaku = false;
    val presenter = LoginPresenter(this);
    override fun setLayout(): Int {
        return R.layout.activity_login
    }

    override fun init() {
        super.init()

        btn_login.setOnClickListener{login()}
        tv_newuser.setOnClickListener { startActivity<RegistActivity>() }
        et_pwd.setOnEditorActionListener { v, actionId, event ->
            login()
            true
        }

        initDanmaku();

    }
    lateinit var danmakucontext:DanmakuContext;
    private fun initDanmaku() {
        danmaku.setCallback(object : DrawHandler.Callback{
            override fun danmakuShown(danmaku: BaseDanmaku?) {

            }

            override fun drawingFinished() {

            }

            override fun prepared() {
                showDanmaku = true
                danmaku.start()
            }

            override fun updateTimer(timer: DanmakuTimer?) {
            }

        });
        danmakucontext = DanmakuContext.create();
        danmaku.enableDanmakuDrawingCache(true)
        val danmakuparser = DanmaKuParser();
        danmaku.prepare(danmakuparser,danmakucontext)

    }

    private fun login(){
        hideSoftKeyBorad()


        if(hasWritePermission()){
            val username = et_username.text.toString().trim();
            val pwd = et_pwd.text.toString().trim();
//            if(!TextUtils.isEmpty(username)){
//                addDanmaku(username,true);
//                et_username.setText("")
//                et_pwd.setText("")
//            }
            presenter.login(username,pwd)
        }else{
            applyWritePermission();
        }

    }

    private fun applyWritePermission() {
        val permission = arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        ActivityCompat.requestPermissions(this,permission,0);
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if(grantResults[0] == PackageManager.PERMISSION_GRANTED && requestCode == 0){
            login()
        }else{
            toast("权限获取失败")
        }
    }


    private fun hasWritePermission(): Boolean {
        val result =
            ActivityCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);

        return result == PackageManager.PERMISSION_GRANTED

    }

    override fun userNameError() {
        et_username.error = getString(R.string.user_name_error)
    }

    override fun pwdError() {
        et_pwd.error = "密码输入错误";
    }

    override fun loginStaus() {
        showProgressDialog("正在登陆")
    }

    override fun loginSuccess() {
        dismissProgressDialog()
        startActivity<MainActivity>()
        finish()
    }


    inner class DanmaKuParser:BaseDanmakuParser(){
        override fun parse(): IDanmakus {
            return Danmakus()
        }
    }

    override fun loginFail() {
        dismissProgressDialog()
        toast("登陆失败")




    }

    private fun addDanmaku(username: String, b: Boolean) {

        val dan:BaseDanmaku = danmakucontext.mDanmakuFactory.createDanmaku(BaseDanmaku.TYPE_SCROLL_RL)

        dan.text = username
        dan.padding = 6
        dan.textSize = 20f
        dan.textColor = Color.BLACK
        dan.time = danmaku.currentTime
        if(b){
            dan.borderColor = Color.RED
        }
        danmaku.addDanmaku(dan)
        danmaku.show()

    }


    override fun onPause() {
        super.onPause()

        if(danmaku != null && danmaku.isPrepared){
            danmaku.pause()
        }

    }

    override fun onResume() {
        super.onResume()
        if(danmaku != null && danmaku.isPrepared && danmaku.isPaused()){
            danmaku.resume()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        showDanmaku = false;
        if(danmaku != null){
            danmaku.release()
        }
    }

}