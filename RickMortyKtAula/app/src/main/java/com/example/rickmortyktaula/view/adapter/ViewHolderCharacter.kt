package com.example.rickmortyktaula.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.rickmortyktaula.R
import kotlinx.android.synthetic.main.item_recycler.view.*


class ViewHolderCharacter(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val image: ImageView = itemView.findViewById(R.id.imgcharacter_card)
    val name: TextView = itemView.findViewById(R.id.txt_card)

}