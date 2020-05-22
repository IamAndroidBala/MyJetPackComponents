package com.android.myjetpackcomponent.views

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.android.myjetpackcomponent.R

class MainActivity : BaseActivity() {

    override fun contentView() =
        R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Handler().postDelayed({

            startActivity(
                Intent(this@MainActivity,
                HomeActivity::class.java)
            )

            finish()

        }, 1000)

    }

    override fun setScreenTitle() = ""

    override fun toolbarBackPressed() {
        onBackPressed()
    }

}
