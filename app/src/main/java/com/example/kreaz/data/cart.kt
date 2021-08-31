package com.example.kreaz.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class cart(
    @PrimaryKey()
    val id: Int = 0,
    @ColumnInfo(name = "name")
    val itemName: String = "add items",
    @ColumnInfo(name = "price")
    val itemPrice: Float = 0f,
    @ColumnInfo(name = "quantity")
    val quantity: Int = 0,
)
