package com.example.shoppinglistapp.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import com.example.shoppinglistapp.models.ListItem
import com.example.shoppinglistapp.repositories.ListItemRepository

class AddListTypeViewModel : ViewModel() {

    private val _state = mutableStateOf(ListTypeState())
    val state: State<ListTypeState> = _state

    // Função para atualizar o nome da lista
    fun onNameChange(name: String) {
        _state.value = _state.value.copy(name = name)
    }

    // Função para atualizar a descrição da lista
    fun onDescriptionChange(description: String) {
        _state.value = _state.value.copy(description = description)
    }

    // Função para adicionar a lista ao Firebase
    fun addList() {
        val listItem = ListItem(
            name = _state.value.name,
            description = _state.value.description
        )
        ListItemRepository.add(listItem) {
            // Adiciona lógica adicional após a lista ser adicionada com sucesso
        }
    }
}

data class ListTypeState(
    val name: String = "",
    val description: String = ""
)
