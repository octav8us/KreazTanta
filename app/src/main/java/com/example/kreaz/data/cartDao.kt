package com.example.kreaz.data

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {

    @Query("SELECT * from cart ORDER BY name ASC")
    fun getItems(): Flow<List<cart>>


    @Query("SELECT SUM(price)FROM cart WHERE quantity>0")
    fun getTotal(): Flow<Int>

    @Query("DELETE  FROM cart")
    fun cleanTabel()


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: cart)

    @Update
    suspend fun update(item: cart)

    @Query("DELETE FROM cart WHERE id = :id")
    suspend fun delete(id: Int)


}
