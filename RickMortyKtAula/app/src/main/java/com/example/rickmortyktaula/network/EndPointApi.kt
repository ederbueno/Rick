package com.example.rickmortyktaula.network

import com.example.rickmortyktaula.model.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface EndPointApi {

    @GET("character")
    suspend fun getResponseCharacter() : CharacterResponse
    //@Query("chave")

    @GET("localization")
    suspend fun getResponseLocalization()

    @GET("episode")
    suspend fun getResponseEpisode()

}