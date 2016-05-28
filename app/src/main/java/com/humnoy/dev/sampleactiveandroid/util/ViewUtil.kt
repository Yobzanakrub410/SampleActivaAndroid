package com.humnoy.dev.sampleactiveandroid.util

import android.view.View

/**
 * Created by humnoyDeveloper
 * วันที่ 25/5/59.
 * เวลา 19:11
 * SampleActiveAndroid
 */
class ViewUtil{
    companion object{
        fun <T : View> find(view: View, id:Int) : T {
            return view.findViewById(id) as T
        }
    }
}
