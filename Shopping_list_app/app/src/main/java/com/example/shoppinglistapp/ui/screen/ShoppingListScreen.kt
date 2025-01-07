package com.example.shoppinglistapp.ui.screen

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import com.example.shoppinglistapp.data.model.ShoppingItem
import com.example.shoppinglistapp.data.repository.ShoppingListFirestoreRepository
import com.example.shoppinglistapp.utils.FirebaseUtils
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingListScreen(context: Context) {
    val userId = FirebaseUtils.getCurrentUser()?.uid ?: ""
    val shoppingListRepository = ShoppingListFirestoreRepository()

    val shoppingItems = remember { mutableStateOf<List<ShoppingItem>>(emptyList()) }
    val newItem = remember { mutableStateOf("") }

    LaunchedEffect(userId) {
        shoppingListRepository.getShoppingList(userId) { items ->
            shoppingItems.value = items
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        shoppingItems.value.forEach { item ->
            Text(text = item.name)
        }

        TextField(
            value = newItem.value,
            onValueChange = { newItem.value = it },
            label = { Text("Novo Item") }
        )

        Button(onClick = {
            val item = ShoppingItem(name = newItem.value, quantity = 1)
            shoppingListRepository.saveShoppingList(userId, listOf(item))
            shoppingItems.value = shoppingItems.value + item
        }) {
            Text("Adicionar Item")
        }
    }
}
