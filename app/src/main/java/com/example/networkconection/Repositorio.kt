package com.example.networkconection

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Repositorio(
    val id: Int,
    val name: String,
    val owner: Owner,
    @SerializedName("html_url")
    val link: String,
    val description: String
) : Parcelable