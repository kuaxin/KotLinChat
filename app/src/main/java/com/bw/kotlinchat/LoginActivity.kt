package com.bw.kotlinchat

import android.view.KeyEvent
import android.widget.TextView
import android.widget.Toast
import com.bw.kotlinchat.contract.LoginContract
import com.bw.kotlinchat.presenter.LoginPresenter
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.toast

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
        val username = et_username.text.toString().trim();
        val pwd = et_pwd.text.toString().trim();

        presenter.login(username,pwd)

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