package com.example.rickmortykt.remote

import com.example.rickmortykt.model.CharacterResponse
import retrofit2.http.GET

interface ServiceRickMorty{

    @GET("/character")
    suspend fun getCharacterApi() : CharacterResponse
}