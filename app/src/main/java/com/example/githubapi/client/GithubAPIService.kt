package com.example.githubapi.client

import com.example.githubapi.client.model.ReadMe
import com.example.githubapi.client.model.ResponseAPI
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface GitHubService {

    @GET("search/repositories/")
    fun listRepos(
        @Query("q") query: String?, @Query("sort") sort: String?,
        @Query("order") order: String
    ): Call<ResponseAPI>

    @GET("search/repositories?q=android&sort=stars&order=desc&per_page=100")
    fun hardcodedCall(): Call<ResponseAPI>

    @GET("/repos/{owner}/{repo}/readme")
    fun getReadMeLink(
        @Path("owner") owner: String?,
        @Path("repo") repo: String?
    ): Call<ReadMe>

}

