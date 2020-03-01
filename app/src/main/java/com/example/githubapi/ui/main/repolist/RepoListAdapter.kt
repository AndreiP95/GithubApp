package com.example.githubapi.ui.main.repolist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.githubapi.MainActivity
import com.example.githubapi.R
import com.example.githubapi.client.model.Repo

class RepoListAdapter(
    val activity: MainActivity,
    val elements: List<Repo>
) : RecyclerView.Adapter<RepoListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(
            parent.context
        ).inflate(R.layout.repo_element, parent, false)

        return ViewHolder(view, this)
    }

    fun switchFragments(element: Repo) {
        activity.switchFragments(element)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(elements.get(position))
    }

    override fun getItemCount(): Int {
        return elements.size
    }

    class ViewHolder(
        itemView: View,
        val adapter: RepoListAdapter
    ) : RecyclerView.ViewHolder(itemView) {

        val layout: View = itemView.findViewById(R.id.repo_element_layout)
        val titleTextView: TextView = itemView.findViewById(R.id.repo_element_title)
        val subtitleTextView: TextView = itemView.findViewById(R.id.repo_element_subtitle)
        val stelute: TextView = itemView.findViewById(R.id.repo_element_stelute)

        fun bindItem(element: Repo) {

            layout.setOnClickListener { adapter.switchFragments(element) }

            titleTextView.setText(element.full_name)
            subtitleTextView.setText(element.url)
            stelute.setText(element.stars.toString())
        }

    }
}