package com.example.githubapi.ui.main.repolist

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubapi.MainActivity
import com.example.githubapi.R
import com.example.githubapi.client.GithubClient
import com.example.githubapi.client.model.ResponseAPI
import com.example.githubapi.ui.main.repodetails.RepoDetailsViewModel

class RepoList : Fragment() {

    companion object {
        fun newInstance() =
            RepoList()
    }

    private lateinit var viewModel: RepoDetailsViewModel
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.repos_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.repos_recycler_view)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(RepoDetailsViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()
        GithubClient.instance.getRepoList(this)
    }

    private fun setAdapter(responseAPI: ResponseAPI) {
        recyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = RepoListAdapter(activity as MainActivity, responseAPI.list)
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()

    }

    fun populateViews(responseAPI: ResponseAPI) {
        setAdapter(responseAPI)
    }

}
