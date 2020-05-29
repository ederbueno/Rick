package com.example.rickmortykt.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickmortykt.R
import com.example.rickmortykt.model.Result
import com.example.rickmortykt.view.adapter.AdapterCharacter
import com.example.rickmortykt.viewmodel.ViewModelCharacter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    private var results = mutableListOf<Result>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recycler = recycler_view_home


        val viewModelCharacter =  ViewModelProviders.of(this).get(ViewModelCharacter::class.java)
        val adapterCharacter = AdapterCharacter(results, this)
        recycler.adapter = adapterCharacter
        val layoutManager = LinearLayoutManager(this)
        recycler.layoutManager = layoutManager

        viewModelCharacter.getAllCharacters()
        viewModelCharacter.listCharacter.observe(this, Observer {
            results.addAll(it)
            adapterCharacter.notifyDataSetChanged()
            }
        )
    }
}
