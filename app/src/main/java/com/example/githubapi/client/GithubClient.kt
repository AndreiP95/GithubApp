package com.example.githubapi.client

import android.content.Context
import android.util.Log
import com.example.githubapi.Constants
import com.example.githubapi.client.model.ReadMe
import com.example.githubapi.client.model.ResponseAPI
import com.example.githubapi.ui.main.repodetails.RepoDetailsFragment
import com.example.githubapi.ui.main.repolist.RepoList
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GithubClient(val context: Context) {

    lateinit var client: OkHttpClient
    lateinit var retrofit: Retrofit
    lateinit var service: GitHubService

    companion object {
        lateinit var instance: GithubClient

        fun instanceClient(context: Context) {
            if (!::instance.isInitialized) {
                instance = GithubClient(context)
                instance.setRetroFit()
            }

        }
    }

    fun setRetroFit() {

        client = OkHttpClient().newBuilder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                this.setLevel(HttpLoggingInterceptor.Level.BASIC)
            })
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(GitHubService::class.java)

    }

    fun getReadme(
        title: String?,
        fragment: RepoDetailsFragment
    ) {
        val spliter: List<String>? = title?.split("/")
        val call: Call<ReadMe> = service.getReadMeLink(
            spliter?.get(0),
            spliter?.get(1)
        )
        call.enqueue(
            object : Callback<ReadMe> {
                override fun onFailure(call: Call<ReadMe>, t: Throwable) {
                    Log.d("TAG", t.localizedMessage)
                }

                override fun onResponse(call: Call<ReadMe>, response: Response<ReadMe>) {
                    response.body()?.let {
                        fragment.goToReadme(it.html_url)
                    }

                }
            })
    }

    fun getRepoList(fragment: RepoList) {
        val call: Call<ResponseAPI> = service.hardcodedCall()
        call.enqueue(object : Callback<ResponseAPI> {
            override fun onFailure(call: Call<ResponseAPI>, t: Throwable) {
                Log.d("TAG", t.localizedMessage)
            }

            override fun onResponse(call: Call<ResponseAPI>, response: Response<ResponseAPI>) {
                response.body()?.let {
                    fragment.populateViews(it)
                }

            }
        })

    }


}