package com.example.kreaz.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kreaz.network.CategoriesApi
import com.example.kreaz.network.branch
import kotlinx.coroutines.launch

class BranchesViewModel : ViewModel() {
    private val _Branches = MutableLiveData<List<branch>>()

    val branches: LiveData<List<branch>> = _Branches


     init {
         getBranchesLive()
     }

    private fun getBranchesLive() {
        try {


            viewModelScope.launch {
                val listResult = CategoriesApi.retrofitService.getBranches()
                _Branches.value = listResult.data


            }
        } catch (e: Exception) {


        }
    }


}