package com.example.nytimesnewsapp.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import com.example.nytimesnewsapp.model.NYTResponse

interface NYTApiService {
    @GET("topstories/v2/{section}.json")
    suspend fun getTopStories(
        @Path("section") section: String,
        @Query("api-key") apiKey: String
    ): Response<NYTResponse>
}
