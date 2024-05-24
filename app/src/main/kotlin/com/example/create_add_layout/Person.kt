package com.example.create_add_layout

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Person(
    var name: String = "",
    var phone: String = "",
    var age: Int = 0,
    var hobby: String = "",
    var sex: String = ""
): Parcelable