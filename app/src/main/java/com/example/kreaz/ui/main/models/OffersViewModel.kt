package com.example.kreaz.ui.main.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kreaz.network.KreazApi
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
                val listResult = KreazApi.retrofitService.getOffers()
                _Offers.value = listResult.data


            }
        } catch (e: Exception) {


        }
    }
}