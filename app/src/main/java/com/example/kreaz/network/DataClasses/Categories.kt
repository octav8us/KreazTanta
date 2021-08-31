package com.example.kreaz.network

import com.squareup.moshi.Json


data class CategoriesResponse(

    @Json(name = "data")
    val `data`: List<CategoriesResponseData>?,
    @Json(name = "type")
    val type: String?
)

data class CategoriesResponseData(
    @Json(name = "icon")
    val icon: String?,
    @Json(name = "id")
    val id: String?,
    @Json(name = "items")
    val items: List<Item>?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "total_items")
    val totalItems: String?
)

data class Item(
    @Json(name = "des")
    val des: String?,
    @Json(name = "id")
    val id: String?,
    @Json(name = "img")
    val img: String?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "price")
    val price: String?,
    @Json(name = "unit")
    val unit: String?
)