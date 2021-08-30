package com.example.kreaz.network

import com.google.gson.annotations.SerializedName

data class CartOrder(

    @SerializedName("items")
    val items: String?,
    @SerializedName("name")
    val name: String?,

    @SerializedName("mobile")
    val mobile: String?


)

data class CartOrderResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("type")
    val type: String
) {
    data class Data(
        @SerializedName("status")
        val status: Int,
        @SerializedName("time")
        val time: String,
        @SerializedName("timestamp")
        val timestamp: Int,
        @SerializedName("title")
        val title: String
    )
}