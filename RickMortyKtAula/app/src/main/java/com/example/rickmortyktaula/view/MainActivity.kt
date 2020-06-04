package com.example.rickmortyktaula.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.GridLayout
import android.widget.Toast
import com.example.rickmortyktaula.R
import com.example.rickmortyktaula.WebViewAct
import com.example.rickmortyktaula.model.Result
import com.example.rickmortyktaula.view.adapter.AdapterCharacter
import com.example.rickmortyktaula.viewmodel.CharacterViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var results = mutableSetOf<Result>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recycler = findViewById<RecyclerView>(R.id.recycler_home)
        val webViewBtn = findViewById<Button>(R.id.btn_webview)

        webViewBtn.setOnClickListener(View.OnClickListener { startActivity(Intent(this, WebViewAct::class.java)) })

        //como se fosse fidby do viewModel
        val viewModelCharacter =  ViewModelProviders.of(this).get(CharacterViewModel::class.java)
        //config recycler
        val adapterCharacter = AdapterCharacter(results, this)
        recycler.adapter = adapterCharacter
        val layoutManager = GridLayoutManager(this, 2)
        recycler.layoutManager = layoutManager

        viewModelCharacter.getAllCharacters()
        viewModelCharacter.listMutableCharacter.observe(this, Observer {
            it?.let { itChar -> results.addAll(itChar) }
            adapterCharacter.notifyDataSetChanged()
        })
    }
}
