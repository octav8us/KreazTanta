package com.example.kreaz.network

data class Branches(
    val `data`: List<branch>,
    val type: String
)

data class branch(
    val address: String,
    val branch_id: String,
    val icon: String,
    val img: String,
    val lat: String,
    val lon: String,
    val name: String,
    val phones: List<String>,
    val times: String
)