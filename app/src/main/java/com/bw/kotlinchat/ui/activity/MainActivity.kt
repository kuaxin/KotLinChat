package com.bw.kotlinchat.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.internal.BottomNavigationItemView
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.NavigationView
import android.view.MenuItem
import com.bw.kotlinchat.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun setLayout(): Int {
        return R.layout.activity_main
    }

    override fun init() {
        super.init()
        bottomNaBar.setOnNavigationItemSelectedListener(object : BottomNavigationView.OnNavigationItemSelectedListener{
            override fun onNavigationItemSelected(p0: MenuItem): Boolean {
                when(p0.itemId){

                }
                return true;
            }

        })



    }

}
