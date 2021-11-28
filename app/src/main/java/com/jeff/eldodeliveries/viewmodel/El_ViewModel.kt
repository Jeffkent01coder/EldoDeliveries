package com.jeff.eldodeliveries.viewmodel

import androidx.lifecycle.ViewModel
import com.jeff.eldodeliveries.database.Items
import com.jeff.eldodeliveries.repository.El_Repository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class El_ViewModel (private val repository: El_Repository) : ViewModel(){

    fun insert(items: Items) = GlobalScope.launch {
        repository.insert(items)
    }

    fun delete(items: Items) = GlobalScope.launch {
        repository.delete(items)
    }

    fun getAllEldoItems() = repository.getAllItems()
}