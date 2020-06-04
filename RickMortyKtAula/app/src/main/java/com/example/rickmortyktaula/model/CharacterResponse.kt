package com.example.rickmortyktaula.model

data class CharacterResponse(
    val info: Info,
    val results: MutableSet<Result>
)