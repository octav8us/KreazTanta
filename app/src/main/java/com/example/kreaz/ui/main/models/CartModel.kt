package com.example.kreaz.ui.main.models

import android.util.Log
import androidx.lifecycle.*
import com.example.kreaz.data.CartDao
import com.example.kreaz.data.cart
import com.example.kreaz.network.CartOrder
import com.example.kreaz.network.CartOrderResponse
import com.example.kreaz.network.KreazApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class CartModel(private val cartDao: CartDao) : ViewModel() {

    private val _status = MutableLiveData<String>()
    val status: LiveData<String> = _status


    var cartItems: LiveData<List<cart>> = cartDao.getItems()

    val total: LiveData<Int> = cartDao.getTotal()

    private var _response = MutableLiveData<CartOrderResponse>()
    var Response: LiveData<CartOrderResponse> = _response


    fun deleteItem(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            cartDao.delete(id)
        }
    }


    fun SendOrder(cart: List<cart>) {


        viewModelScope.launch {
            var orderLast = CartOrderEntry(cart)
            Log.v(
                "magdy_data",
                "index=${orderLast.items + " " + orderLast.mobile + " " + orderLast.name}"
            )

            var order = async {
                KreazApi.retrofitService.postToCart(
                    items = orderLast.items!!,
                    name = orderLast.name!!,
                    mobile = orderLast.mobile!!
                )
            }
            _response.postValue(order.await())

            Log.v("magdy", "index=${order.await()?.data}")

        }


    }

    private fun clean() {
        viewModelScope.launch {
            cartDao.cleanTabel()
        }
    }

    fun Clean() {
        clean()
    }

    private fun CartOrderEntry(cart: List<cart>): CartOrder {
        var items = ""

        cart.map {
            items += "${it.id}:${it.quantity},"
        }
        items = items.dropLast(1)
        return CartOrder(
            items = items,
            name = "amr",
            mobile = "01091802000"

        )
    }

    class ItemsViewModelFactory(private val cartdao: CartDao) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CartModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return CartModel(cartdao) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }


    }
}

