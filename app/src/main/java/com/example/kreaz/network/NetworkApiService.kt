package com.example.kreaz.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private const val BASE_URL = "http://kreazdessert.com/api/"


private val retrofit by lazy {
    Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()
}

object KreazApi {
    val retrofitService: ApiService by lazy { retrofit.create(ApiService::class.java) }
}

