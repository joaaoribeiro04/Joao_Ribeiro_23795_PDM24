package com.example.shoppinglistapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.shoppinglistapp.ui.home.ListTypesView
import com.example.shoppinglistapp.ui.login.LoginView
import com.example.shoppinglistapp.ui.theme.ShoppingListAppTheme
import com.example.shoppinglistapp.ui.home.AddListTypeView
import com.example.shoppinglistapp.ui.home.ShareListView

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            ShoppingListAppTheme {
                NavHost(navController = navController, startDestination = "login") {
                    composable("login") {
                        LoginView {
                            navController.navigate("list_types")
                        }
                    }
                    composable("list_types") {
                        ListTypesView(navController = navController)
                    }
                    composable("add_list_type") {
                        AddListTypeView(navController = navController)
                    }
                    composable("share_list/{listId}") { backStackEntry ->
                        val listId = backStackEntry.arguments?.getString("listId")
                        if (listId != null) {
                            ShareListView(navController = navController, listId = listId)
                        }
                    }
                }
            }
        }
    }
}
