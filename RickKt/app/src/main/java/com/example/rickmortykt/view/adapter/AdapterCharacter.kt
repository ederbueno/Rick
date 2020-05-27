package com.example.rickmortykt.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickmortykt.R
import com.example.rickmortykt.model.Result
import com.squareup.picasso.Picasso

class AdapterCharacter(
    private val list: List<Result>,
    private val context: Context
) : RecyclerView.Adapter<ViewHolderCharacter>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCharacter {
     val view = LayoutInflater.from(context).inflate(R.layout.item_recycler, parent, false)
        return ViewHolderCharacter(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolderCharacter, position: Int) {
        val character = list[position]
        Picasso.get().load(character.image).into(holder.image)
        holder.name.text = character.name
    }

}