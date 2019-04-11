package com.bw.kotlinchat

import android.widget.Toast
import com.bw.kotlinchat.contract.LoginContract
import kotlinx.android.synthetic.main.activity_login.*

/**
 * Create by Rgx on 2019/4/11 10:37
 * Description:
 */
class LoginActivity:BaseActivity(),LoginContract.View {


    override fun setLayout(): Int {
        return R.layout.activity_login
    }

    override fun init() {
        super.init()


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
        Toast.makeText(this,"登陆失败",Toast.LENGTH_LONG).show();
    }



}