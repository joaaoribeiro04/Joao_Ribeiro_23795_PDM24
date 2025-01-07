package com.example.shoppinglistapp.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.shoppinglistapp.viewmodel.AddListTypeViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun AddListTypeView(navController: NavController) {
    val viewModel: AddListTypeViewModel = viewModel()

    val state = viewModel.state.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = state.name,
            onValueChange = { viewModel.onNameChange(it) },
            placeholder = { Text("List Name") }
        )
        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = state.description,
            onValueChange = { viewModel.onDescriptionChange(it) },
            placeholder = { Text("List Description") }
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            viewModel.addList()
            navController.popBackStack()
        }) {
            Text("Add")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddListTypeViewPreview() {
    val navController = rememberNavController()
    MaterialTheme {
        AddListTypeView(navController = navController)
    }
}
