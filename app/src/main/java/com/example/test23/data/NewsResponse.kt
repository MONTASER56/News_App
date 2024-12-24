package com.example.test23.data


import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @SerializedName("articles") val articles: List<Article>,
    @SerializedName("status") val status: String?,
    @SerializedName("totalResults") val totalResults: Int?
)