package com.humnoy.dev.sampleactiveandroid.model

import com.activeandroid.Model
import com.activeandroid.annotation.Column
import com.activeandroid.annotation.Table

/**
 * Created by humnoyDeveloper
 * วันที่ 25/5/59.
 * เวลา 09:59
 * SampleActivaAndroid
 */

@Table(name = "Categories")
class Category : Model() {
    @Column(name = "Name")
    var name :String? = null
}