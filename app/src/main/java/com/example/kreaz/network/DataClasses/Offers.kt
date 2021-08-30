package com.example.kreaz.network

data class Offers(
    val `data`: List<offer>,
    val type: String
)

data class offer(
    val address: String,
    val branch_id: String,
    val date: String,
    val icon: String,
    val img: String,
    val lat: String,
    val lon: String,
    val name: String,
    val offer_id: String,
    val offer_img: String,
    val phones: List<String>,
    val times: String,
    val valid: Int
)