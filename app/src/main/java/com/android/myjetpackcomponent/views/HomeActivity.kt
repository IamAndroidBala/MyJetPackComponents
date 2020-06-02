package com.android.myjetpackcomponent.views

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.android.myjetpackcomponent.R
import com.android.myjetpackcomponent.coroutines.CoroutineActivity
import com.android.myjetpackcomponent.databinding.DataBindingActivity
import com.android.myjetpackcomponent.livedata.LiveDataActivity
import com.android.myjetpackcomponent.workmanager.WorkManagerActivity
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseActivity(), View.OnClickListener {

    override fun contentView() = R.layout.activity_home

    override fun setScreenTitle() = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        tvLiveData.setOnClickListener(this)
        tvCoroutine.setOnClickListener(this)
        tvWorkManager.setOnClickListener(this)
        tvDataBinding.setOnClickListener(this)

    }

    override fun toolbarBackPressed() = onBackPressed()

    override fun onClick(v: View?) {

        when(v?.id) {

            R.id.tvLiveData -> {
                startActivity(Intent(this@HomeActivity, LiveDataActivity::class.java))
            }

            R.id.tvCoroutine -> {
                startActivity(Intent(this@HomeActivity, CoroutineActivity::class.java))
            }

            R.id.tvDataBinding -> {
                startActivity(Intent(this@HomeActivity, DataBindingActivity::class.java))
            }

            R.id.tvWorkManager -> {
                startActivity(Intent(this@HomeActivity, WorkManagerActivity::class.java))
            }

        }

    }

}