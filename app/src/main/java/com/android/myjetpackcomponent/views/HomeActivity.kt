package com.android.myjetpackcomponent.views

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.android.myjetpackcomponent.R
import com.android.myjetpackcomponent.livedata.LiveDataActivity
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseActivity(), View.OnClickListener {

    override fun contentView() =
        R.layout.activity_home

    override fun setScreenTitle() = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        tvLiveData.setOnClickListener(this)

    }

    override fun toolbarBackPressed() = onBackPressed()

    override fun onClick(v: View?) {

        when(v?.id) {

            R.id.tvLiveData -> {
                startActivity(Intent(this@HomeActivity, LiveDataActivity::class.java))
            }

        }

    }

}