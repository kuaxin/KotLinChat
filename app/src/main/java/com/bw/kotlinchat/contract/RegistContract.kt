package com.bw.kotlinchat.contract

/**
 * Create by Rgx on 2019/4/11 19:02
 * Description:
 */
interface RegistContract {


    interface Persenter{
        fun register(userName:String,pwd:String,confirmPwd:String)
    }

    interface View{
        fun userNameError()
        fun pwdError()
        fun confirmPwdError()
        fun regiestStaus()
        fun regiestSuccess()
        fun regiestFail()
    }

}