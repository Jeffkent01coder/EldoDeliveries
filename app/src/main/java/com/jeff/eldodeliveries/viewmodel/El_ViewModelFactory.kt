package com.jeff.eldodeliveries.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jeff.eldodeliveries.repository.El_Repository

class El_ViewModelFactory (private val repository: El_Repository): ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return El_ViewModel(repository) as T
    }
}