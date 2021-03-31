package com.example.rickmortyktaula.modelMovies

data class MovieConfiguration(
    val change_keys: List<String>? = listOf(),
    val images: Images? = Images()
)