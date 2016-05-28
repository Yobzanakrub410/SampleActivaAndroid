package com.humnoy.dev.sampleactiveandroid

import com.activeandroid.ActiveAndroid
import com.activeandroid.app.Application

/**
 * Created by humnoyDeveloper
 * วันที่ 25/5/59.
 * เวลา 09:50
 * SampleActivaAndroid
 */
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        ActiveAndroid.initialize(this)
    }
}