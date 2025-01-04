package com.example.nytimesnewsapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nytimesnewsapp.model.NewsArticle
import com.example.nytimesnewsapp.network.RetrofitClient
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {
    private val _news = MutableLiveData<List<NewsArticle>>()
    val news: LiveData<List<NewsArticle>> = _news

    init {
        fetchTopStories("technology")
    }

    private fun fetchTopStories(section: String) {
        viewModelScope.launch {
            val response = RetrofitClient.api.getTopStories(section, "FPZL54kGqBTJAAlJAMZ9oD7aQk81qUu2")
            if (response.isSuccessful) {
                _news.postValue(response.body()?.results)
            }
        }
    }
}