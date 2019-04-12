package com.bw.kotlinchat

import android.view.KeyEvent
import android.view.View
import android.widget.TextView
import com.bw.kotlinchat.contract.RegistContract
import com.bw.kotlinchat.presenter.RegisterPersenter
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast

/**
 * Create by Rgx on 2019/4/11 15:47
 * Description:
 */
class RegistActivity:BaseActivity(),RegistContract.View {
    val presenter = RegisterPersenter(this);

    override fun init() {
        super.init()

        register.setOnClickListener { registerDemo(); }
        confirmPassword.setOnEditorActionListener { v, actionId, event ->
            registerDemo()
            true
        }
    }

    private fun registerDemo() {
        hideSoftKeyBorad()
        val username = userName.text.toString().trim();
        val password = password.text.toString().trim();
        val confirmpwd = confirmPassword.text.toString().trim();
        presenter.register(username,password,confirmpwd)
    }

    override fun userNameError() {
        userName.error = getString(R.string.user_name_error)
    }

    override fun pwdError() {
        password.error = getString(R.string.password_error)
    }

    override fun confirmPwdError() {
        confirmPassword.error = getString(R.string.confirm_password_error)
    }

    override fun regiestStaus() {
        showProgressDialog("正在注册")
    }

    override fun regiestSuccess() {
        dismissProgressDialog()
        toast("注册成功")
        finish()
    }

    override fun regiestFail() {
        dismissProgressDialog()
        toast("注册失败")
    }

    override fun setLayout(): Int {
        return R.layout.activity_register
    }
}