package com.example.githubapi.client.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ReadMe(
    @SerializedName("html_url")
    val html_url : String
) : Serializable