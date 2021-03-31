package com.example.rickmortyktaula.repository

import com.example.rickmortyktaula.modelMovies.MovieConfiguration

object SingletonConfiguration {
    var config: MovieConfiguration? = null
    fun setConfiguration(configuration: MovieConfiguration){
        config = configuration
    }
}