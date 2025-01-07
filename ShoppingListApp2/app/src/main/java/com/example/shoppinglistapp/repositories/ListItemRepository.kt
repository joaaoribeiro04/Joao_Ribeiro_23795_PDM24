package com.example.shoppinglistapp.repositories

import android.util.Log
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.example.shoppinglistapp.models.ListItem

object ListItemRepository {

    private val db = Firebase.firestore

    fun shareList(listId: String, userId: String, onSuccess: () -> Unit) {
        db.collection("listTypes")
            .document(listId)
            .update("owners", FieldValue.arrayUnion(userId))
            .addOnSuccessListener {
                Log.d("ListItemRepository", "List $listId shared with user $userId")
                onSuccess()
            }
            .addOnFailureListener { e ->
                Log.w("ListItemRepository", "Error sharing list $listId with user $userId", e)
            }
    }

    fun add(listItem: ListItem, onAddListSuccess: () -> Unit) {
        val currentUser = Firebase.auth.currentUser
        currentUser?.uid?.let {
            listItem.owners = arrayListOf(it)
        }

        db.collection("listTypes")
            .add(listItem)
            .addOnSuccessListener { documentReference ->
                Log.d("TAG", "DocumentSnapshot added with ID: ${documentReference.id}")
                onAddListSuccess()
            }
            .addOnFailureListener { e ->
                Log.w("TAG", "Error adding document", e)
            }
    }

    fun getAll(onSuccess: (List<ListItem>) -> Unit) {
        val currentUser = Firebase.auth.currentUser
        currentUser?.uid?.let {
            db.collection("listTypes")
                .whereArrayContains("owners", it)
                .addSnapshotListener { value, error ->
                    if (error != null) {
                        Log.w("ListItemRepository", "Error getting lists", error)
                        return@addSnapshotListener
                    }

                    val listItems = value?.documents?.mapNotNull { document ->
                        val itemList = document.toObject(ListItem::class.java)
                        itemList?.docId = document.id
                        itemList
                    }
                    listItems?.let { onSuccess(it) }
                }
        }
    }
}
