package com.tuwaiq.bookfinder.notification

import android.window.SplashScreen
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.tuwaiq.bookfinder.MainActivity
import java.util.concurrent.TimeUnit

class NotificationRepo {
        fun myNotification(mainActivity: MainActivity){
            val myWorkRequest= PeriodicWorkRequest
                .Builder(WorkerNotification::class.java,3, TimeUnit.DAYS)
                .setInputData(workDataOf(
                    "title" to "We miss you ",
                    "message" to "come to find your interesting book ")
                )
                .build()
            WorkManager.getInstance(mainActivity).enqueueUniquePeriodicWork(
                "periodicStockWorker",
                ExistingPeriodicWorkPolicy.KEEP,
                myWorkRequest
            )
        }
    }
