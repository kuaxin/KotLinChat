package com.bw.kotlinchat.presenter

import android.util.Log
import android.widget.Toast
import com.bw.kotlinchat.contract.RegistContract
import com.bw.kotlinchat.extentions.checkPwd
import com.bw.kotlinchat.extentions.checkUserName
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.SaveListener
import com.bw.kotlinchat.bean.UserBean
import com.bw.kotlinchat.util.toast


/**
 * Create by Rgx on 2019/4/11 20:24
 * Description:
 */
class RegisterPersenter(val view:RegistContract.View):RegistContract.Persenter {
    override fun register(userName: String, pwd: String, confirmPwd: String) {
        if(userName.checkUserName()){
            if(pwd.checkPwd()){
                if(confirmPwd.equals(pwd)){
                    view.regiestStaus()
                    BmobRegiest(userName,pwd);
                }else{
                    view.confirmPwdError()
                }
            }else{
                view.pwdError()
            }
        }else{
            view.userNameError()
        }
    }

    private fun BmobRegiest(userName: String, pwd: String) {

        val p2 = UserBean(userName,pwd)

        p2.save(object : SaveListener<String>() {
            override fun done(objectId: String, e: BmobException?) {
                if (e == null) {
                    Log.v("RGX","成功");

                } else {
                    Log.v("RGX","失败");
                    view.regiestFail()
                }
            }
        })

    }
}