package com.example.githubapi.client.model

import com.google.gson.annotations.SerializedName

data class ResponseAPI(
    @SerializedName("total_count")
    val total_results: Int = 0,
    @SerializedName("items")
    val list: List<Repo>
)