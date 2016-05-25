package com.humnoy.dev.sampleactivaandroid

import com.activeandroid.ActiveAndroid

/**
 * Created by humnoyDeveloper
 * วันที่ 25/5/59.
 * เวลา 09:50
 * SampleActivaAndroid
 */
class MyApplication : com.activeandroid.app.Application() {
    override fun onCreate() {
        super.onCreate()
        ActiveAndroid.initialize(this)
    }
}