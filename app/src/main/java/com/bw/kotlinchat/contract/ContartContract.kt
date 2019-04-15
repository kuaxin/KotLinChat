package com.bw.kotlinchat.contract

/**
 * Create by Rgx on 2019/4/15 11:47
 * Description:
 */
interface ContartContract {

    interface Persenter:BasePresenter{
        fun getContartData()
    }

    interface View{
        fun contartDataSuccess()
        fun contartDataError()
    }


}