/*
package com.example.kreaz.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData

class dataRepository (val Dao: CartDao){


    var cartItems: LiveData<List<cart>> =Dao.items.asLiveData()


    //      cartDao.getItems().asLiveData() as MutableLiveData<List<cart>>
    var total : LiveData<Int> =Dao.total.asLiveData()
    //cartDao.getTotal().asLiveData()

    suspend fun refreshCart (){
        try {


            var dataList =Dao.items.asLiveData()

            cartItems =dataList

            var dataTotal =Dao.total.asLiveData()
            total =dataTotal
        } catch (e: Exception) {
        }



    }}*/
