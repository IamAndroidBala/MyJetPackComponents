package com.android.myjetpackcomponent.coroutines

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.android.myjetpackcomponent.R
import com.android.myjetpackcomponent.utils.AppLog
import com.android.myjetpackcomponent.utils.SnowFilter
import com.android.myjetpackcomponent.views.BaseActivity
import kotlinx.android.synthetic.main.activity_coroutine.*
import kotlinx.coroutines.*
import java.net.URL

class CoroutineActivity : BaseActivity() {

    private val parentJob = Job()

    private val coroutineExceptionHandler : CoroutineExceptionHandler = CoroutineExceptionHandler {_, throwable ->

        coroutineScope.launch {
            Toast.makeText(applicationContext, throwable.localizedMessage, Toast.LENGTH_LONG ).show()
        }

        GlobalScope.launch {
            AppLog.d("${throwable.localizedMessage}")
        }

    }

    private val coroutineScope = CoroutineScope(Dispatchers.Main + parentJob + coroutineExceptionHandler)

    override fun contentView() = R.layout.activity_coroutine

    override fun setScreenTitle() = getString(R.string.coroutine)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        coroutineScope.launch(Dispatchers.IO) {

            val originalBitmap = getOriginalBitmapAsync(getString(R.string.co_image))

            val snowFilterBitmap = loadSnowFilterAsync(originalBitmap)

            loadImage(snowFilterBitmap)

        }

    }

    private suspend fun getOriginalBitmapAsync(url: String): Bitmap = withContext(Dispatchers.IO) {
            URL(url).openStream().use {
                return@withContext BitmapFactory.decodeStream(it)
            }
        }

    private suspend fun loadSnowFilterAsync(originalBitmap: Bitmap): Bitmap = withContext(Dispatchers.Default) {
            SnowFilter.applySnowEffect(originalBitmap)
        }

    private fun loadImage(snowFilterBitmap: Bitmap) {
        runOnUiThread {
            progressBar.visibility = View.GONE
            imageCorotine?.setImageBitmap(snowFilterBitmap)
        }
    }

    override fun toolbarBackPressed() {
        onBackPressed()
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineScope.cancel()
    }

}