package com.bw.kotlinchat.extentions

/**
 * Create by Rgx on 2019/4/11 14:01
 * Description:
 */
fun String.checkUserName():Boolean{

    return this.matches(Regex("^[a-zA-Z]\\w{2,19}$"))
}

//TODO $表示匹配结尾
fun String.checkPwd():Boolean{
    return this.matches(Regex("^[0-9]{3,20}$"))
}