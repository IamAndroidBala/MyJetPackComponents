package com.android.myjetpackcomponent.workmanager

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.work.WorkInfo
import com.android.myjetpackcomponent.R
import com.android.myjetpackcomponent.databinding.ActivityWorkmanagerBinding
import com.android.myjetpackcomponent.utils.AppLog
import com.android.myjetpackcomponent.utils.TAG_OUTPUT
import com.android.myjetpackcomponent.views.BaseActivity
import com.android.myjetpackcomponent.workmanager.viewmodel.WorkerViewModel
import kotlinx.android.synthetic.main.activity_workmanager.*

class WorkManagerActivity : BaseActivity() {

    lateinit var workerViewModel : WorkerViewModel
    lateinit var binding : ActivityWorkmanagerBinding

    override fun contentView() = R.layout.activity_workmanager

    override fun setScreenTitle() = getString(R.string.wmanager)

    override fun toolbarBackPressed() = onBackPressed()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        workerViewModel = ViewModelProviders.of(this).get(WorkerViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_workmanager)

        workerViewModel.outputWorkInfo.observe(this, workInfoObserver())

    }


    private fun workInfoObserver(): Observer<List<WorkInfo>> {
        return Observer { listOfWorkInfo ->

            if (listOfWorkInfo.isNullOrEmpty()) {
                return@Observer
            }

            val workInfo = listOfWorkInfo[0]

            if (workInfo.state.isFinished) {
                tvSum.text = workInfo.outputData.getString("OUTPUT")
            }

        }
    }

}