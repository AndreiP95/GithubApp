package com.example.githubapi.client.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Repo(
    @SerializedName("full_name")
    val full_name: String = "",
    @SerializedName("html_url")
    val url: String = "",
    @SerializedName("watchers_count")
    val watchers: Int = 0,
    @SerializedName("stargazers_count")
    val stars: Int = 0
) : Serializable