package com.example.kreaz.network

import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


private const val BASE_URL = "http://kreazdessert.com/api/"


private val retrofit by lazy {
    Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()
}

object CategoriesApi {
    val retrofitService: ApiService by lazy { retrofit.create(ApiService::class.java) }
}
