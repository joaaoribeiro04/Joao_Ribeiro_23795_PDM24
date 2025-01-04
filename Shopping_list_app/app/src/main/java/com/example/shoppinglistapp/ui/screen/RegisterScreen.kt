package com.example.shoppinglistapp.ui.screen

import android.widget.Toast
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.shoppinglistapp.utils.FirebaseUtils

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen() {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val message = remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = email.value,
            onValueChange = { email.value = it },
            label = { Text("Email") }
        )
        TextField(
            value = password.value,
            onValueChange = { password.value = it },
            label = { Text("Password") }
        )
        Button(onClick = {
            if (email.value.isNotEmpty() && password.value.isNotEmpty()) {
                FirebaseUtils.registerUser(email.value, password.value) { success, msg ->
                    message.value = msg
                    if (success) {
                    }
                }
            }
        }) {
            Text("Register")
        }
        Text(text = message.value)
    }
}