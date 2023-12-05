package com.example.networkconection.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Base(
    val repo: Repository
): Parcelable