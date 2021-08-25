package com.example.kreaz.network

import com.google.gson.annotations.SerializedName


data class CategoriesResponse(
    @SerializedName("data")
    val `data`: List<CategoriesResponseData>?,
    @SerializedName("type")
    val type: String?
)

data class CategoriesResponseData(
    @SerializedName("icon")
    val icon: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("items")
    val items: List<Item>?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("total_items")
    val totalItems: String?
)

data class Item(
    @SerializedName("des")
    val des: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("img")
    val img: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("price")
    val price: String?,
    @SerializedName("unit")
    val unit: String?
)