package com.android.myjetpackcomponent.workmanager.manager

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.android.myjetpackcomponent.utils.AppLog
import com.android.myjetpackcomponent.utils.TAG_OUTPUT
import java.lang.Exception

class CalcWorker (cxt : Context, params : WorkerParameters) : Worker(cxt,params) {

    override fun doWork(): Result {

        return  try {

            var sum = 0

            for(i in 0..5000) {
                sum +=i
            }

            val outputData = workDataOf(TAG_OUTPUT to sum.toString())
            Result.success(outputData)

        }catch (Ex:Exception){
            Result.failure()
        }

    }

}