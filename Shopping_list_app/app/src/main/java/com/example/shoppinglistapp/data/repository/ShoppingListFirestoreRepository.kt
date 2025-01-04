package com.example.shoppinglistapp.data.repository

import com.example.shoppinglistapp.data.model.ShoppingItem
import com.example.shoppinglistapp.utils.FirebaseUtils
import com.google.firebase.firestore.FirebaseFirestore

class ShoppingListFirestoreRepository {

    private val firestore: FirebaseFirestore = FirebaseUtils.firestore

    fun saveShoppingList(userId: String, shoppingList: List<ShoppingItem>) {
        val shoppingListRef = firestore.collection("shopping_lists").document(userId)
        shoppingListRef.set(mapOf("items" to shoppingList))
            .addOnSuccessListener {
            }
            .addOnFailureListener { _ ->
            }
    }

    fun getShoppingList(userId: String, onComplete: (List<ShoppingItem>) -> Unit) {
        val shoppingListRef = firestore.collection("shopping_lists").document(userId)
        shoppingListRef.get()
            .addOnSuccessListener { document ->
                val shoppingItems = document.get("items") as? List<ShoppingItem>
                shoppingItems?.let {
                    onComplete(it)
                }
            }
            .addOnFailureListener { _ ->
            }
    }
}
