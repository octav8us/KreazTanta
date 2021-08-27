package com.example.kreaz.data

import androidx.room.*

@Dao
interface CartDao {

    @Query("SELECT * from cart ORDER BY name ASC")
    suspend fun getItems(): List<cart>
/*
    @get:Query("SELECT * from cart ORDER BY name ASC")
    var items: LiveData<List<cart>>*/


    @Query("SELECT SUM(price)FROM cart WHERE quantity>0")
    suspend fun getTotal(): Int

    @Query("DELETE  FROM cart")
    suspend fun cleanTabel()


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: cart)

    @Update
    suspend fun update(item: cart)

    @Query("DELETE FROM cart WHERE id = :id")
    suspend fun delete(id: Int)


}
