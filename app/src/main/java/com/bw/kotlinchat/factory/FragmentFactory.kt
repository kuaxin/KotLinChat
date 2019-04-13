package com.bw.kotlinchat.factory

import android.support.v4.app.Fragment
import com.bw.kotlinchat.R
import com.bw.kotlinchat.ui.fragment.ContactsFragment
import com.bw.kotlinchat.ui.fragment.ConversationFragment
import com.bw.kotlinchat.ui.fragment.DynamicFragment

/**
 * Create by Rgx on 2019/4/13 9:39
 * Description:
 */
class FragmentFactory private constructor(){

    val conversionf by lazy {
        ConversationFragment()
    }
    val contactsf by lazy {
        ContactsFragment()
    }
    val dynamicf by lazy {
        DynamicFragment()
    }

    companion object {
        val instance = FragmentFactory();


    }

    fun getFragment(tabId:Int):Fragment{
        when(tabId){
            R.id.conversation_tab

        }
    }
}