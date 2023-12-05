package com.example.networkconection.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PullRequest(
    val title: String,
    @SerializedName("html_url")
    val htmlUrl: String,
    val user: User,
    val base: Base
): Parcelable