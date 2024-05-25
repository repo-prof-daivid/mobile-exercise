package com.example.create_add_layout.network

import com.example.create_add_layout.model.House
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("/Houses")
    fun getSpells(): Call<List<House>>

    @GET("/Houses/{id}")
    fun getSpell(@Path("id") id: String): Call<House>

}