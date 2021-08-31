package com.example.kreaz.network

import retrofit2.http.*

interface ApiService {


    @GET("index.php")
    suspend fun getCategories(
        @Query("mode") mode: String = "get",
        @Query("type") type: String = "categories"
    ): CategoriesResponse?

    @GET("index.php")
    suspend fun getBranches(
        @Query("mode") mode: String = "get",
        @Query("type") type: String = "branches",
        @Query("lang") lang: String = "ar"
    ): Branches?


    @GET("index.php")
    suspend fun getOffers(
        @Query("mode") mode: String = "get",
        @Query("type") type: String = "offers"
    ): Offers?


    @POST("index.php")
    @FormUrlEncoded
    suspend fun postToCart(
        @Query("mode") mode: String = "set",
        @Query("type") type: String = "addtocart",
        @Field("items") items: String,
        @Field("name") name: String,
        @Field("mobile") mobile: String
    ): CartOrderResponse?


}