package com.example.test23.network

import com.example.test23.data.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {


    @GET(value = "v2/top-headlines")
    suspend fun getNews(

        @Query("country") con:String="us",
        @Query("page") page: Int ,
        @Query("apiKey") api_key: String="b7d81f18f6b44fe783118f5333102d65"
    ):NewsResponse

    @GET(value = "/v2/everything")
    suspend fun getSearchNews(

        @Query("q") con:String,
        @Query("page") page: Int ,
        @Query("apiKey") api_key: String="b7d81f18f6b44fe783118f5333102d65"
    ):Response<NewsResponse>
}