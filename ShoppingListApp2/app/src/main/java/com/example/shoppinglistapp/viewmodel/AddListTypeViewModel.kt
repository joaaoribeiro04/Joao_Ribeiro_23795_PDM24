package com.example.shoppinglistapp.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import com.example.shoppinglistapp.models.ListItem
import com.example.shoppinglistapp.repositories.ListItemRepository

class AddListTypeViewModel : ViewModel() {

    private val _state = mutableStateOf(ListTypeState())
    val state: State<ListTypeState> = _state

    fun onNameChange(name: String) {
        _state.value = _state.value.copy(name = name)
    }

    fun onDescriptionChange(description: String) {
        _state.value = _state.value.copy(description = description)
    }

    fun addList() {
        val listItem = ListItem(
            name = _state.value.name,
            description = _state.value.description
        )
        ListItemRepository.add(listItem) {
        }
    }
}

data class ListTypeState(
    val name: String = "",
    val description: String = ""
)
