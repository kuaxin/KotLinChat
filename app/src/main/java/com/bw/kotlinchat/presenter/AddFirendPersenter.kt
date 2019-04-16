package com.bw.kotlinchat.presenter

import android.webkit.WebView
import cn.bmob.v3.Bmob
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import com.bw.kotlinchat.bean.UserBean
import com.bw.kotlinchat.contract.AddFriendContract
import com.bw.kotlinchat.data.AddFriendListItem
import com.hyphenate.chat.EMClient
import org.jetbrains.anko.doAsync

/**
 * Create by Rgx on 2019/4/16 11:33
 * Description:
 */
class AddFirendPersenter(val view:AddFriendContract.View):AddFriendContract.Persenter{

    var addFriendItems = mutableListOf<AddFriendListItem>()
    override fun search(key: String) {
        val query = BmobQuery<UserBean>();
        query.addWhereContains("username",key)
            .addWhereNotEqualTo("username",EMClient.getInstance().currentUser);
        query.findObjects(object:FindListener<UserBean>(){
            override fun done(p0: MutableList<UserBean>?, p1: BmobException?) {
                if(p1 == null){

                    doAsync {
                        p0!!.forEach {
                            val addFriendItem = AddFriendListItem(it.username,it.createdAt)
                            addFriendItems.add(addFriendItem)
                        }
                        uiThread { view.onSearchSuccess() }
                    }
                }else{
                    view.onSearchFail()
                }
            }

        })



    }

}