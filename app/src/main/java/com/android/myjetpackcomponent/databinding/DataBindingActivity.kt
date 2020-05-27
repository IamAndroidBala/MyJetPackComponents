package com.android.myjetpackcomponent.databinding

import android.os.Bundle
import com.android.myjetpackcomponent.R
import com.android.myjetpackcomponent.views.BaseActivity

class DataBindingActivity : BaseActivity() {

    override fun contentView() = R.layout.activity_coroutine

    override fun setScreenTitle() = getString(R.string.data_bind)

    override fun toolbarBackPressed() = onBackPressed()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

}