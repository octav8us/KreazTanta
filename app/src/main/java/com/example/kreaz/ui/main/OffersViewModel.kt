package com.example.kreaz.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kreaz.network.CategoriesApi
import com.example.kreaz.network.CategoriesResponseData
import com.example.kreaz.network.Offers
import com.example.kreaz.network.offer
import kotlinx.coroutines.launch

class OffersViewModel : ViewModel() {
    private val _Offers = MutableLiveData<List<offer>>()
    val Offers: LiveData<List<offer>> = _Offers

    init {
        getOffersLive()
    }


    private fun getOffersLive() {
        try {


            viewModelScope.launch {
                val listResult = CategoriesApi.retrofitService.getOffers()
                _Offers.value = listResult.data


            }
        } catch (e: Exception) {


        }
    }
}