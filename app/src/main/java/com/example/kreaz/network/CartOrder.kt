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
