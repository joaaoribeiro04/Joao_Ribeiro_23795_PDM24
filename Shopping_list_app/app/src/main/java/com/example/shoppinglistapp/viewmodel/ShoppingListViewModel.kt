package com.example.shoppinglistapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppinglistapp.data.model.ShoppingItem
import com.example.shoppinglistapp.data.repository.ShoppingItemRepository
import kotlinx.coroutines.launch

class ShoppingListViewModel(private val repository: ShoppingItemRepository) : ViewModel() {

    fun addItem(item: ShoppingItem) {
        viewModelScope.launch {
            repository.insertItem(item)
        }
    }

    fun getAllItems() {
        viewModelScope.launch {
        }
    }


    fun deleteItem(itemId: Int) {
        viewModelScope.launch {
            repository.deleteItem(itemId)
        }
    }
}
