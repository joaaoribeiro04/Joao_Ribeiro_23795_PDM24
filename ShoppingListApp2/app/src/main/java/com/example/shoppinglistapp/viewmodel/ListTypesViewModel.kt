package com.example.shoppinglistapp.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.example.shoppinglistapp.models.ListItem
import com.example.shoppinglistapp.repositories.ListItemRepository

// ViewModel para gerenciar a lista de tipos de lista
class ListTypesViewModel : ViewModel() {

    private val _state = MutableStateFlow(ListTypesState())
    val state: StateFlow<ListTypesState> = _state

    // Função para carregar todos os itens da lista
    fun loadListTypes() {
        ListItemRepository.getAll { listItems ->
            _state.value = ListTypesState(listItems = listItems)
        }
    }
}

// Data class para armazenar o estado da tela de tipos de listas
data class ListTypesState(
    val listItems: List<ListItem> = emptyList() // Lista de itens de lista
)
