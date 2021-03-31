package com.example.rickmortyktaula.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.rickmortyktaula.R
import com.example.rickmortyktaula.modelMovies.Movie
import com.example.rickmortyktaula.repository.SingletonConfiguration
import com.squareup.picasso.Picasso

class AdapterCharacter : RecyclerView.Adapter<ViewHolderCharacter>() {
    val list = mutableListOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCharacter {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler, parent, false)
        return ViewHolderCharacter(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolderCharacter, position: Int) {
        val movie = list[position]
        val configuration = SingletonConfiguration.config
        val imageUrl = "${configuration?.images?.base_url}${configuration?.images?.backdrop_sizes?.last()}${movie.backdrop_path}"
        Picasso.get().load(imageUrl).into(holder.image)
        holder.name.text = movie.title
    }

   fun addMovies(movies: List<Movie>){
       list.addAll(movies)
       notifyDataSetChanged()
   }
}
