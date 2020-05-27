package com.android.myjetpackcomponent.utils

import android.util.Log

class AppLog {

    companion object {

        fun  d(message : String) {

            if(message.isNotBlank() && IS_TEST_BUILD) {
                Log.d("MyApp", message)
            }

        }

    }
}