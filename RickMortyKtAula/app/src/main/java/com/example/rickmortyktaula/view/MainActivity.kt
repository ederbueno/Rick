package com.example.rickmortyktaula.view

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickmortyktaula.R
import com.example.rickmortyktaula.view.adapter.AdapterCharacter
import com.example.rickmortyktaula.view.adapter.RecyclerScrollListener
import com.example.rickmortyktaula.viewmodel.CharacterViewModel

class MainActivity : AppCompatActivity() {
    lateinit var adapter: AdapterCharacter
    private val viewModel by lazy { ViewModelProviders.of(this).get(CharacterViewModel::class.java) }
    private val nextPageLoading by lazy { findViewById<ProgressBar>(R.id.nextLoading) }
    private val firstPageLoading by lazy { findViewById<ProgressBar>(R.id.firstLoading) }

    private val recyclerScrollListener by lazy {
        RecyclerScrollListener {
            viewModel.requestNextPage()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recycler = findViewById<RecyclerView>(R.id.recycler_home)

        //config recycler
        adapter = AdapterCharacter()
        recycler.adapter = adapter
        val layoutManager = LinearLayoutManager(this)
        recycler.layoutManager = layoutManager

        recycler.addOnScrollListener(recyclerScrollListener)

        viewModel.moviesList.observe(this) {movies ->
            setRequestingNextPage()
            adapter.addMovies(movies)
        }

        viewModel.nextPageLoading.observe(this) {
            if (it) {
                nextPageLoading.visibility = VISIBLE
            } else {
                nextPageLoading.visibility = GONE
            }
        }

        viewModel.firstPageLoading.observe(this) {
            if (it) {
                firstPageLoading.visibility = VISIBLE
            } else {
                firstPageLoading.visibility = GONE
            }
        }

        viewModel.errorMessage.observe(this, Observer {
            it?.let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun setRequestingNextPage() {
        recyclerScrollListener.requesting = false
    }
}
