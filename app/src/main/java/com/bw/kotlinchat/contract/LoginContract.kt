package com.bw.kotlinchat.contract

/**
 * Create by Rgx on 2019/4/11 10:42
 * Description:
 */
interface LoginContract {

    interface LoginPresenter:BasePresenter{
        fun login(userName:String,pwd:String)
    }

    interface View{
        fun userNameError()
        fun pwdError()
        fun loginStaus()
        fun loginSuccess()
        fun loginFail()
    }
}