package com.example.shoppinglistapp.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.shoppinglistapp.models.ListItem
import com.example.shoppinglistapp.ui.theme.ShoppingListAppTheme
import com.example.shoppinglistapp.viewmodel.ListTypesViewModel

@Composable
fun ListTypesView(navController: NavController) {
    val viewModel: ListTypesViewModel = viewModel()
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadListTypes()
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            LazyColumn {
                items(state.listItems.size) { index ->
                    val item = state.listItems[index]
                    ListTypeRowView(listItem = item)
                }
            }
        }
        Button(
            onClick = { navController.navigate("add_list_type") },
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.BottomEnd)
        ) {
            Text("Add List")
        }
    }
}

@Composable
fun ListTypeRowView(listItem: ListItem) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = listItem.name ?: "Unnamed", style = MaterialTheme.typography.bodyLarge)
        Text(text = listItem.description ?: "No description")
    }
}

@Preview(showBackground = true)
@Composable
fun ListTypesViewPreview() {
    ShoppingListAppTheme {
        val navController = rememberNavController()
        ListTypesView(navController = navController)
    }
}
