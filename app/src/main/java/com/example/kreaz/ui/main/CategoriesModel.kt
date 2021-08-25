package com.example.kreaz.ui.main

import android.util.Log
import androidx.lifecycle.*
import com.example.kreaz.data.CartDao
import com.example.kreaz.data.cart
import com.example.kreaz.network.CategoriesResponse
import com.example.kreaz.network.CategoriesApi
import com.example.kreaz.network.CategoriesResponseData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import okhttp3.Dispatcher

class CategoriesModel(private val cartDao: CartDao) : ViewModel() {

    private val _status = MutableLiveData<String>()
    val status: LiveData<String> = _status


    private val _categories = MutableLiveData<List<CategoriesResponseData>>()
    val categories: LiveData<List<CategoriesResponseData>> = _categories

    val cartItems: LiveData<List<cart>> = cartDao.getItems().asLiveData()
    val total: LiveData<Int> = cartDao.getTotal().asLiveData()

    init {
        getCategoriesLive()
    }


    private fun getCategoriesLive() {
        try {


            viewModelScope.launch(Dispatchers.IO) {
                val listResult = CategoriesApi.retrofitService.getCategories()
                _categories.postValue(listResult.data ?: listOf())


            }
        } catch (e: Exception) {
            _status.value = "Failure: ${e.message}"


        }
    }


    class ItemsViewModelFactory(private val cartdao: CartDao) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CategoriesModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return CategoriesModel(cartdao) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }


    }

//    val allCartItems: LiveData<List<cart>> = cartDao.getItems().asLiveData()

    private fun clean() {
        viewModelScope.launch {
            cartDao.cleanTabel()
        }
    }

    fun Clean() {
        clean()
    }

    fun updateItem(
        itemId: Int,
        itemName: String,
        itemPrice: String,
        itemCount: String
    ) {
        val updatedItem = itemEntry(itemId, itemName, itemPrice, itemCount)
        updateItem(updatedItem)
    }


    private fun updateItem(item: cart) {
        viewModelScope.launch {
            cartDao.update(item)
        }
    }


    fun addNewItem(id: Int, itemName: String, itemPrice: String, itemCount: Int) {
        val newItem = itemEntry(id, itemName, itemPrice, itemCount.toString())
        insertItem(newItem)
    }

    private fun insertItem(item: cart) {
        viewModelScope.launch {
            cartDao.insert(item)
        }
    }


    fun deleteItem(id: Int) {
        viewModelScope.launch {
            cartDao.delete(id)
        }
    }


    private fun itemEntry(
        itemId: Int,
        itemName: String,
        itemPrice: String,
        itemCount: String
    ): cart {
        return cart(
            id = itemId,
            itemName = itemName,
            itemPrice = itemPrice.toFloat(),
            quantity = itemCount.toInt()
        )
    }
}

