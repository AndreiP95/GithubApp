package com.example.githubapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.githubapi.client.GithubClient
import com.example.githubapi.client.model.Repo
import com.example.githubapi.ui.main.repolist.RepoList
import com.example.githubapi.ui.main.repodetails.RepoDetailsFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.detailFragment, RepoList.newInstance())
                .commitNow()
        }
        GithubClient.instanceClient(this)
    }


    fun switchFragments(element: Repo) {

        val bundle = Bundle()
        bundle.putSerializable("repo", element)

        val fragment: RepoDetailsFragment = RepoDetailsFragment.newInstance()
        fragment.setArguments(bundle)

        supportFragmentManager.beginTransaction()
            .replace(R.id.detailFragment, fragment)
            .addToBackStack("detail")
            .commit()

    }

}
