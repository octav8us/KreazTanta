package com.example.kreaz.network

import com.squareup.moshi.Json

data class CartOrder(
    @Json(name = "items")
    val items: String?,


    @Json(name = "name")
    val name: String?,


    @Json(name = "mobile")
    val mobile: String?


)

data class CartOrderResponse(

    @Json(name = "data")
    val `data`: Data,

    @Json(name = "type")
    val type: String
) {
    data class Data(

        @Json(name = "status")
        val status: Int,

        @Json(name = "time")
        val time: String,

        @Json(name = "timestamp")
        val timestamp: Int,

        @Json(name = "title")
        val title: String
    )
}