package com.example.kreaz.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {


    @GET("index.php")
    suspend fun getCategories(
        @Query("mode") mode: String = "get",
        @Query("type") type: String = "categories"
    ): CategoriesResponse

    @GET("index.php")
    suspend fun getBranches(
        @Query("mode") mode: String = "get",
        @Query("type") type: String = "branches",
        @Query("lang") lang: String = "ar"
    ): Branches


    @GET("index.php")
    suspend fun getOffers(
        @Query("mode") mode: String = "get",
        @Query("type") type: String = "offers"
    ): Offers


    @POST("index.php")
    suspend fun postToCart(
        @Query("mode") mode: String = "set",
        @Query("type") type: String = "addtocart"

    )


}