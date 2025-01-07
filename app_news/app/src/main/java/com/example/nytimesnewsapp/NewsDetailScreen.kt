package com.example.nytimesnewsapp

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.nytimesnewsapp.viewmodel.NewsViewModel

@Composable
fun NewsDetailScreen(url: String, viewModel: NewsViewModel) {
    val newsDetail by viewModel.newsDetail.observeAsState(null)

    LaunchedEffect(url) {
        viewModel.fetchNewsDetail(url)
    }

    if (newsDetail != null) {
        val news = newsDetail!!
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = news.title, style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = news.abstract, style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Leia mais: ${news.url}", style = MaterialTheme.typography.bodySmall)
        }
    } else {
        CircularProgressIndicator(modifier = Modifier.fillMaxSize())
    }
}