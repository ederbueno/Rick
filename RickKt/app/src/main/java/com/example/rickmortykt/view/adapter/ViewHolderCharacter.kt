package com.example.rickmortykt.view.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_recycler.view.*

class ViewHolderCharacter(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val image: ImageView = itemView.imgcharacter_card
    val name: TextView = itemView.txt_card

}
