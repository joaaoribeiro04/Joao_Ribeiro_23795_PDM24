package com.example.nytimesnewsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.nytimesnewsapp.viewmodel.NewsViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: NewsViewModel = viewModel()
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "newsList") {
                composable("newsList") {
                    NewsListScreen(viewModel, navController)
                }
                composable(
                    "newsDetail/{url}",
                    arguments = listOf(
                        navArgument("url") { type = NavType.StringType }
                    )
                ) { backStackEntry ->
                    val url = backStackEntry.arguments?.getString("url") ?: ""
                    NewsDetailScreen(url = url, viewModel = viewModel)
                }
            }
        }
    }
}
