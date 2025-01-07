package com.example.nytimesnewsapp.model

data class NYTResponse(val results: List<NewsArticle>)

data class NewsArticle(
    val title: String,
    val abstract: String,
    val url: String,
    val multimedia: List<Multimedia>?
)

data class Multimedia(
    val url: String,
    val format: String
)
