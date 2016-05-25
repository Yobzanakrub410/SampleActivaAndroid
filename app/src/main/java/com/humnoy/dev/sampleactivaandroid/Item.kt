package com.humnoy.dev.sampleactivaandroid

import com.activeandroid.Model
import com.activeandroid.annotation.Column
import com.activeandroid.annotation.Table

/**
 * Created by humnoyDeveloper
 * วันที่ 25/5/59.
 * เวลา 10:03
 * SampleActivaAndroid
 */

@Table(name = "Items")
class Item : Model {
    @Column(name = "Name") var name :String = ""
    @Column(name = "Category") var category : Category? = null

    constructor():super()
    constructor(name : String , category: Category?):super(){
            this.name = name
            this.category = category
    }
}