package com.example.create_add_layout

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    var uid: String? = null,
    var name: String? = null,
    var email: String? = null,
    var phone: String? = null,
): Parcelable {

    val contactList: ArrayList<Person> = arrayListOf()

}