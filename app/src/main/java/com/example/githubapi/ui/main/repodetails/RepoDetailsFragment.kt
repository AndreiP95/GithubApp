package com.example.githubapi.ui.main.repodetails

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.githubapi.MainActivity
import com.example.githubapi.R
import com.example.githubapi.client.GithubClient
import com.example.githubapi.client.model.Repo

class RepoDetailsFragment : Fragment() {

    companion object {
        fun newInstance() =
            RepoDetailsFragment()
    }

    private lateinit var viewModel: RepoDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.repos_details, container, false)
    }

    lateinit var title: TextView
    lateinit var url: TextView
    lateinit var stars: TextView
    lateinit var watchers: TextView
    lateinit var readme: Button

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(RepoDetailsViewModel::class.java)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        readme = view?.findViewById(R.id.repo_details_button_readme)
        title = view?.findViewById(R.id.repo_details_title)
        url = view?.findViewById(R.id.repo_details_url)
        watchers = view?.findViewById(R.id.repo_details_watchers)
        stars = view?.findViewById(R.id.repo_details_stars)

        val repo = this.arguments?.get("repo") as Repo?

        title.setText(repo?.full_name)
        url.setText(repo?.url)
        stars?.setText(repo?.stars?.toString())
        watchers?.setText(repo?.watchers?.toString())

        readme?.setOnClickListener {
            GithubClient.instance.getReadme(
                repo?.full_name, this
            )
        }

    }

    fun goToReadme(url: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setData(Uri.parse(url))
        startActivity(intent)
    }


}
