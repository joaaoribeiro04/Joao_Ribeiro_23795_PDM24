package com.example.shoppinglistapp.models

data class ListItem(
    var docId: String? = null,
    var name: String? = null,
    var description: String? = null,
    var owners: List<String>? = null
)
