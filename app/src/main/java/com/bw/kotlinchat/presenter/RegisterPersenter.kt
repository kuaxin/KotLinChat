package com.bw.kotlinchat.presenter

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.bw.kotlinchat.contract.RegistContract
import com.bw.kotlinchat.extentions.checkPwd
import com.bw.kotlinchat.extentions.checkUserName
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.SaveListener
import cn.bmob.v3.listener.UpdateListener
import com.bw.kotlinchat.bean.UserBean
import com.bw.kotlinchat.util.toast
import com.hyphenate.chat.EMClient
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread

/**
 * Create by Rgx on 2019/4/11 20:24
 * Description:
 */
class RegisterPersenter(val context:Context,val view:RegistContract.View):RegistContract.Persenter {

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
                    doAsync {
                        try {

                            EMClient.getInstance().createAccount(userName, pwd);
                            uiThread { view.regiestSuccess() }
                        } catch (e: Exception) {
                            p2.delete(object:UpdateListener(){
                                override fun done(p0: BmobException?) {
                                    if(p0 == null){
                                        uiThread { view.alreadyRegiest() }
                                    }else{

                                    }
                                }
                            })

                        }
                    }
                } else {
                    Log.v("RGX","失败");
                }
            }
        })

    }

}