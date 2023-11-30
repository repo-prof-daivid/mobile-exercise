package com.example.networkconection.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Owner(
    val id: String,
    val login: String,
    @SerializedName("avatar_url")
    val avatarUrl: String
): Parcelable