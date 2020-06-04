package com.example.rickmortyktaula

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.widget.Button

class WebViewAct : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        val webViewTeste = findViewById<WebView>(R.id.webView)
        val btnVolta = findViewById<Button>(R.id.voltar)

        btnVolta.setOnClickListener( { onBackPressed() })
        webViewTeste.loadUrl("https://kotlinlang.org/docs/reference/")
    }
}
