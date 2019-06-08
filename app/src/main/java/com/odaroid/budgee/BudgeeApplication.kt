package com.odaroid.budgee

import android.app.Application
import timber.log.Timber

class BudgeeApplication :Application(){
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}