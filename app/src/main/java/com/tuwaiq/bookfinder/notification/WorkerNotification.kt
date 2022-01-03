package com.tuwaiq.bookfinder.notification

import android.content.Context

import androidx.work.Worker
import androidx.work.WorkerParameters

class WorkerNotification(private val context: Context, workParams: WorkerParameters): Worker(context, workParams)  {
    override fun doWork(): Result {
        NotificationHelper(context).createNotification(
            inputData.getString("title").toString(),
            inputData.getString("message").toString())
        return Result.success()
    }

}