package com.example.rickmortyktaula.repository

import com.example.rickmortyktaula.model.CharacterResponse
import com.example.rickmortyktaula.network.EndPointApi
import com.example.rickmortyktaula.network.RetrofitInit



class RepositoryRickMorty{

    private var url = "https://rickandmortyapi.com/api/"
    private var service = EndPointApi::class
    //private var chave = "asdpoighasiohjgoisahjg"

    private val serviceRick = RetrofitInit(url).create(service)

    suspend fun getCharacterService(): CharacterResponse = serviceRick.getResponseCharacter()
}