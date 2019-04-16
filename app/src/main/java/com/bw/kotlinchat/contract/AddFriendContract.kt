package com.bw.kotlinchat.contract

/**
 * Create by Rgx on 2019/4/16 11:30
 * Description:
 */
interface AddFriendContract {

    interface Persenter:BasePresenter{
        fun search(key:String)
    }

    interface View{
        fun onSearchSuccess()
        fun onSearchFail()
    }
}