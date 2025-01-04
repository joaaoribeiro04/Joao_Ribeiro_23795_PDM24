package com.example.nytimesnewsapp

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.nytimesnewsapp.model.NewsArticle
import com.example.nytimesnewsapp.viewmodel.NewsViewModel
import androidx.compose.ui.platform.LocalContext
import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: NewsViewModel = viewModel()
            NewsListScreen(viewModel)
        }
    }
}

@Composable
fun NewsListScreen(viewModel: NewsViewModel) {
    val newsList by viewModel.news.observeAsState(emptyList())

    LazyColumn {
        items(newsList) { news ->
            NewsItem(news)
        }
    }
}

@Composable
fun NewsItem(news: NewsArticle) {
    val context = LocalContext.current

    Column(modifier = androidx.compose.ui.Modifier.padding(8.dp)) {
        Text(text = news.title, style = MaterialTheme.typography.titleLarge)
        Text(text = news.abstract)
        Button(onClick = {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(news.url))
            ContextCompat.startActivity(context, intent, null)
        }) {
            Text("Ler Mais")
        }
        Spacer(modifier = androidx.compose.ui.Modifier.height(16.dp))
    }
}
