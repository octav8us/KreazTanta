package com.example.kreaz.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CartDao {

    @Query("SELECT * from cart ORDER BY name ASC")
    fun getItems(): LiveData<List<cart>>
/*
    @get:Query("SELECT * from cart ORDER BY name ASC")
    var items: LiveData<List<cart>>*/


    @Query("SELECT SUM(price)FROM cart WHERE quantity>0")
    fun getTotal(): LiveData<Int>

    @Query("DELETE  FROM cart")
    suspend fun cleanTabel()


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: cart)

    @Update
    suspend fun update(item: cart)

    @Query("DELETE FROM cart WHERE id = :id")
    suspend fun delete(id: Int)


}
