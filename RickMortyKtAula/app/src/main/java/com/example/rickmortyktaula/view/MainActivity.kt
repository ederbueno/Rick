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
import com.example.rickmortyktaula.viewmodel.CharacterViewModel

class MainActivity : AppCompatActivity() {
    lateinit var adapter: AdapterCharacter
    var requesting = true
    val nextPageLoading by lazy { findViewById<ProgressBar>(R.id.nextLoading) }
    val firstPageLoading by lazy { findViewById<ProgressBar>(R.id.firstLoading) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recycler = findViewById<RecyclerView>(R.id.recycler_home)

        //como se fosse fidby do viewModel
        val viewModelCharacter = ViewModelProviders.of(this).get(CharacterViewModel::class.java)

        //config recycler
        adapter = AdapterCharacter()
        recycler.adapter = adapter
        val layoutManager = LinearLayoutManager(this)
        recycler.layoutManager = layoutManager

        recycler.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val target = recyclerView.layoutManager as LinearLayoutManager
                val totalItemCount = target.itemCount
                val lastVisible = target.findLastVisibleItemPosition()
                val lastItem = lastVisible + 5 >= totalItemCount
                if (totalItemCount > 0 && lastItem && !requesting){
                    setRequestingNextPage(true)
                    viewModelCharacter.requestNextPage()
                }
            }
        })

        viewModelCharacter.moviesList.observe(this) {
            setRequestingNextPage(false)
            adapter.addMovies(it)
        }

        viewModelCharacter.nextPageLoading.observe(this){
            if (it){
                nextPageLoading.visibility = VISIBLE
            }else{
                nextPageLoading.visibility = GONE
            }
        }

        viewModelCharacter.firstPageLoading.observe(this){
            if (it){
                firstPageLoading.visibility = VISIBLE
            }else{
                firstPageLoading.visibility = GONE
            }
        }

        viewModelCharacter.errorMessage.observe(this, Observer {
            it?.let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun setRequestingNextPage(requesting: Boolean) {
        this.requesting = requesting
    }
}
