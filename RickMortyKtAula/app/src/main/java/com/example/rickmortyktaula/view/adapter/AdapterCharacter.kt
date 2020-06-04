package com.example.rickmortyktaula.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.rickmortyktaula.R
import com.example.rickmortyktaula.model.Result
import com.squareup.picasso.Picasso

class AdapterCharacter(
    private val list: MutableSet<Result>,
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
        val character = list.elementAt(position)
        Picasso.get().load(character.image).into(holder.image)
        holder.name.text = character.name
    }

}
