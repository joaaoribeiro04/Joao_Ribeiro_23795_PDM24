package com.example.shoppinglistapp.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.example.shoppinglistapp.models.ListItem
import com.example.shoppinglistapp.repositories.ListItemRepository

class ListTypesViewModel : ViewModel() {

    private val _state = MutableStateFlow(ListTypesState())
    val state: StateFlow<ListTypesState> = _state

    fun loadListTypes() {
        ListItemRepository.getAll { listItems ->
            _state.value = ListTypesState(listItems = listItems)
        }
    }
}

data class ListTypesState(
    val listItems: List<ListItem> = emptyList()
)
