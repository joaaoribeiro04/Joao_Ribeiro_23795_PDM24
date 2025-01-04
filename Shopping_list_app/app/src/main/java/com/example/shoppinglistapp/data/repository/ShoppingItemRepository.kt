package com.example.shoppinglistapp.data.repository

import android.content.Context
import com.example.shoppinglistapp.data.model.ShoppingItem

class ShoppingItemRepository(context: Context) {
    private val shoppingListDatabase = ShoppingListDatabase.getDatabase(context)
    private val shoppingItemDao = shoppingListDatabase.shoppingItemDao()

    suspend fun insertItem(item: ShoppingItem) {
        shoppingItemDao.insertItem(item)
    }

    suspend fun getAllItems(): List<ShoppingItem> {
        return shoppingItemDao.getAllItems()
    }

    suspend fun deleteItem(itemId: Int) {
        shoppingItemDao.deleteItem(itemId)
    }
}
