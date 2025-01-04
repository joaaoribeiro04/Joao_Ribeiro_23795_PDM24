package com.example.shoppinglistapp.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import androidx.compose.ui.platform.LocalContext
import com.example.shoppinglistapp.ui.screen.LoginScreen
import com.example.shoppinglistapp.ui.screen.RegisterScreen
import com.example.shoppinglistapp.ui.screen.ShoppingListScreen
import com.example.shoppinglistapp.ui.theme.ShoppingListAppTheme
import com.example.shoppinglistapp.utils.FirebaseUtils

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShoppingListAppTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    val user = FirebaseUtils.getCurrentUser()

    NavHost(navController = navController, startDestination = if (user == null) "login" else "shoppingList") {
        composable("login") {
            LoginScreen()
        }
        composable("register") {
            RegisterScreen()
        }
        composable("shoppingList") {
            ShoppingListScreen(context = LocalContext.current)
        }
    }
}
