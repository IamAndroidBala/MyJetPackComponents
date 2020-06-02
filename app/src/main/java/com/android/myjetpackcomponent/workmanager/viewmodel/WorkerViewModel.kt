package com.android.myjetpackcomponent.workmanager.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.work.*
import com.android.myjetpackcomponent.utils.TAG_OUTPUT
import com.android.myjetpackcomponent.workmanager.manager.CalcWorker

class WorkerViewModel(application: Application) : AndroidViewModel(application) {

    private val workManager = WorkManager.getInstance(application)
    internal val outputWorkInfo: LiveData<List<WorkInfo>>

    init {
        outputWorkInfo=  workManager.getWorkInfosByTagLiveData(TAG_OUTPUT)
        calculateNumbers()
    }

    private fun calculateNumbers() {

        val constraints = Constraints.Builder()
            .setRequiresCharging(true)
            .build()

        val cal = OneTimeWorkRequestBuilder<CalcWorker>()
            .setConstraints(constraints)
            .addTag(TAG_OUTPUT)
            .build()

        workManager.beginWith(cal).enqueue()

    }

}