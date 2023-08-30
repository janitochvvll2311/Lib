package com.juandev.lib.test

import android.app.Application
import timber.log.Timber

class TestApp : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        Timber.d("TestApp_TAG: onCreate")
    }

}