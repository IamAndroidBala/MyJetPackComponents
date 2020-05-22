package com.android.myjetpackcomponent.views

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.android.myjetpackcomponent.R

abstract class BaseActivity : AppCompatActivity() {

    abstract fun setScreenTitle() : String

    abstract fun toolbarBackPressed()

    abstract fun contentView() : Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(contentView())

        setToolbar()

    }

    private fun setToolbar() {

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back_24dp)
        supportActionBar?.title = setScreenTitle()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return if (item.itemId == android.R.id.home) {
            toolbarBackPressed()
            true
        } else
            true

    }

}