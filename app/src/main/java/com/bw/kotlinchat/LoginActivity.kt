package com.bw.kotlinchat

import android.app.Activity
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.view.KeyEvent
import android.webkit.PermissionRequest
import android.widget.TextView
import android.widget.Toast
import com.bw.kotlinchat.contract.LoginContract
import com.bw.kotlinchat.presenter.LoginPresenter
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.toast
import java.util.jar.Manifest

/**
 * Create by Rgx on 2019/4/11 10:37
 * Description:
 */
class LoginActivity:BaseActivity(),LoginContract.View {

    val presenter = LoginPresenter(this);
    override fun setLayout(): Int {
        return R.layout.activity_login
    }

    override fun init() {
        super.init()

        btn_login.setOnClickListener{login()}
        et_pwd.setOnEditorActionListener { v, actionId, event ->
            login()
            true
        }


    }

    private fun login(){
        hideSoftKeyBorad()

        if(hasWritePermission()){
            val username = et_username.text.toString().trim();
            val pwd = et_pwd.text.toString().trim();
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
        et_username.error = getString(R.string.username_error)
    }

    override fun pwdError() {
        et_pwd.error = "密码输入错误";
    }

    override fun loginStaus() {
        showProgressDialog("正在登陆")
    }

    override fun loginSuccess() {
        dismissProgressDialog()
        startActivity1(MainActivity().javaClass,true);
    }

    override fun loginFail() {
        dismissProgressDialog()
        toast("登陆失败")
    }



}