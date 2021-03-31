package com.example.rickmortyktaula.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.rickmortyktaula.R
import com.example.rickmortyktaula.modelMovies.Movie
import com.example.rickmortyktaula.repository.SingletonConfiguration
import com.squareup.picasso.Picasso

class AdapterCharacter : PagingDataAdapter<Movie, ViewHolderCharacter>(MOVIE_COMPARATOR) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCharacter {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler, parent, false)
        return ViewHolderCharacter(view)
    }

    override fun onBindViewHolder(holder: ViewHolderCharacter, position: Int) {
        val movie = getItem(position)
        val configuration = SingletonConfiguration.config
        val imageUrl = "${configuration?.images?.base_url}${configuration?.images?.backdrop_sizes?.first()}${movie?.backdrop_path}"
        Picasso.get().load(imageUrl).into(holder.image)
        holder.name.text = movie?.title
    }

    companion object {
        val MOVIE_COMPARATOR = object : DiffUtil.ItemCallback<Movie>() {
            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem.title == newItem.title

            override fun getChangePayload(oldItem: Movie, newItem: Movie): Nothing? = null
        }
    }
}
