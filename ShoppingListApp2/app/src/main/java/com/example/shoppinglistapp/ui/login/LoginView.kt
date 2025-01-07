package com.example.shoppinglistapp.ui.login

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.FirebaseAuth

@Composable
fun LoginView(onLoginSuccess: () -> Unit) {
    val auth = FirebaseAuth.getInstance()

    val state = remember { mutableStateOf(LoginState()) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = state.value.username,
            onValueChange = { state.value = state.value.copy(username = it) },
            placeholder = { Text(text = "Email") }
        )
        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = state.value.password,
            onValueChange = { state.value = state.value.copy(password = it) },
            placeholder = { Text(text = "Password") }
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            if (state.value.username.isNotEmpty() && state.value.password.isNotEmpty()) {
                auth.signInWithEmailAndPassword(state.value.username, state.value.password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Log.d("TAG", "Login Success")
                            onLoginSuccess()
                        } else {
                            Log.w("TAG", "Login Failed", task.exception)
                        }
                    }
            }
        }) {
            Text("Login")
        }

        if (state.value.error != null) {
            Text(state.value.error ?: "", color = MaterialTheme.colorScheme.error)
        }
    }
}

data class LoginState(
    val username: String = "",
    val password: String = "",
    val error: String? = null
)
