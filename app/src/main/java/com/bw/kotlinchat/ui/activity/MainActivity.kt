package com.bw.kotlinchat.ui.activity


import com.bw.kotlinchat.R
import com.bw.kotlinchat.factory.FragmentFactory
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity() {
    override fun setLayout(): Int {
        return R.layout.activity_main
    }

    override fun init() {
        super.init()
        bottomNaBar.setOnNavigationItemSelectedListener { p0 ->
            val beginTransaction = supportFragmentManager.beginTransaction();
            beginTransaction.replace(R.id.fragment_frame, FragmentFactory.instance.getFragment(p0.itemId)!!);
            beginTransaction.commit()

            true;
        }


    }

}
