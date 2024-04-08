package com.example.create_add_layout

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Person(
    val name: String,
    val phone: String,
    val age: Int,
    val hobby: String,
    val sex: String
): Parcelable