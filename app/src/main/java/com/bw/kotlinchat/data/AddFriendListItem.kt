package com.bw.kotlinchat.data

import java.util.concurrent.atomic.DoubleAdder

/**
 * Create by Rgx on 2019/4/15 14:14
 * Description:
 */
data class AddFriendListItem(var username: String, var timestart: String, val isAdder: Boolean = false) {
}