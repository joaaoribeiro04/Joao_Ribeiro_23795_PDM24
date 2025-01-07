package com.example.shoppinglistapp.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.shoppinglistapp.models.User
import com.example.shoppinglistapp.repositories.ListItemRepository
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun ShareListView(navController: NavController, listId: String) {
    val users = remember { mutableStateOf<List<User>>(emptyList()) }
    val selectedUsers = remember { mutableStateOf<Set<User>>(emptySet()) }

    LaunchedEffect(Unit) {
        FirebaseFirestore.getInstance().collection("users")
            .get()
            .addOnSuccessListener { result ->
                users.value = result.documents.map { it.toObject(User::class.java)!! }
            }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Share List")

        LazyColumn {
            items(users.value, key = { it.id }) { user ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Checkbox(
                        checked = selectedUsers.value.contains(user),
                        onCheckedChange = {
                            selectedUsers.value = if (it) {
                                selectedUsers.value + user
                            } else {
                                selectedUsers.value - user
                            }
                        }
                    )
                    Text(user.name)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            selectedUsers.value.forEach { user ->
                ListItemRepository.shareList(listId, user.id) {
                    navController.popBackStack()
                }
            }
        }) {
            Text("Share List")
        }
    }
}
