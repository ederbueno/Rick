package com.example.rickmortykt.model

data class CharacterResponse(
    val info: Info,
    val results: List<Result>
)