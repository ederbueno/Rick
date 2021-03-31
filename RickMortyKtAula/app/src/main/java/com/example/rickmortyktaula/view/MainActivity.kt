package com.example.rickmortyktaula.view

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.rickmortyktaula.R
import com.example.rickmortyktaula.model.Result
import com.example.rickmortyktaula.modelMovies.Movie
import com.example.rickmortyktaula.view.adapter.AdapterCharacter
import com.example.rickmortyktaula.view.adapter.MoviesLoadStateAdapter
import com.example.rickmortyktaula.viewmodel.CharacterViewModel
import kotlinx.coroutines.flow.collectLatest

class MainActivity : AppCompatActivity() {
    lateinit var adapter: AdapterCharacter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recycler = findViewById<RecyclerView>(R.id.recycler_home)

        //como se fosse fidby do viewModel
        val viewModelCharacter = ViewModelProviders.of(this).get(CharacterViewModel::class.java)
        //config recycler



//        viewModelCharacter.moviesList.observe(this, Observer { movies ->
//            val adapterCharacter = AdapterCharacter(movies ?: listOf(), this)
//            recycler.adapter = adapterCharacter
//            val layoutManager =
//                GridLayoutManager(this, 2)
//            recycler.layoutManager = layoutManager
//        })

        adapter = AdapterCharacter()
            recycler.adapter = adapter
            val layoutManager =
                GridLayoutManager(this, 2)
            recycler.layoutManager = layoutManager

        recycler.adapter = adapter.withLoadStateHeaderAndFooter(
            header = MoviesLoadStateAdapter(adapter),
            footer = MoviesLoadStateAdapter(adapter)
        )

        lifecycleScope.launchWhenCreated {
            viewModelCharacter.movies.collectLatest {
                adapter.submitData(it)
            }
        }

        viewModelCharacter.errorMessage.observe(this, Observer {
            it?.let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        })
    }
}
