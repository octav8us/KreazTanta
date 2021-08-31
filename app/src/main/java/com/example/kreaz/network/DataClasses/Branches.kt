package com.example.kreaz.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


data class Branches(
    @JsonClass(generateAdapter = true)

    @Json(name = "data")
    val `data`: List<branch>,
    @Json(name = "type")
    val type: String
) {
    @JsonClass(generateAdapter = true)
    data class branch(
        @Json(name = "address")
        val address: String,
        @Json(name = "branch_id")
        val branchId: String,
        @Json(name = "icon")
        val icon: String,
        @Json(name = "img")
        val img: String,
        @Json(name = "lat")
        val lat: String,
        @Json(name = "lon")
        val lon: String,
        @Json(name = "name")
        val name: String,
        @Json(name = "phones")
        val phones: List<String>,
        @Json(name = "times")
        val times: String
    )
}