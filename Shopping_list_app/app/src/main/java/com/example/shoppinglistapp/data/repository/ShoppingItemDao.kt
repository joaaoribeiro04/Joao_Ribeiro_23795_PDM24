package com.example.shoppinglistapp.data.repository


import com.example.shoppinglistapp.data.model.ShoppingItem


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ShoppingItemDao {
    @Insert
    suspend fun insertItem(item: ShoppingItem)

    @Query("SELECT * FROM shopping_items")
    suspend fun getAllItems(): List<ShoppingItem>

    @Query("DELETE FROM shopping_items WHERE id = :itemId")
    suspend fun deleteItem(itemId: Int)
}