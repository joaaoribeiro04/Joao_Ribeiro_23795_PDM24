package com.example.nytimesnewsapp

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.nytimesnewsapp.model.NewsArticle
import com.example.nytimesnewsapp.viewmodel.NewsViewModel
import androidx.navigation.NavController


@Composable
fun NewsListScreen(viewModel: NewsViewModel, navController: NavController) {
    val newsList = viewModel.news.observeAsState(emptyList())

    LazyColumn {
        items(newsList.value) { news ->
            NewsItem(news) {
                val encodedUrl = Uri.encode(news.url)
                navController.navigate("newsDetail/$encodedUrl")
            }
        }
    }
}

@Composable
fun NewsItem(news: NewsArticle, onReadMoreClick: () -> Unit) {
    Column(modifier = Modifier.padding(8.dp)) {
        news.multimedia?.let {
            val imageUrl = it.firstOrNull()?.url
            imageUrl?.let {
                Image(
                    painter = rememberImagePainter(imageUrl),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth().height(200.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        Text(text = news.title, style = MaterialTheme.typography.titleLarge)
        Text(text = news.abstract)
        Button(onClick = onReadMoreClick) {
            Text("Ler Mais")
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}
