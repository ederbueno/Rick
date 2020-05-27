package com.example.rickmortykt.repository

import com.example.rickmortykt.model.CharacterResponse
import com.example.rickmortykt.remote.RetroInit
import com.example.rickmortykt.remote.ServiceRickMorty

class RepositoryRickMorty {
    private var url = "https://rickandmortyapi.com/api/"
    private var service = ServiceRickMorty::class

    private val serviceRick = RetroInit(url).create(service)

    suspend fun getServiceRick(): CharacterResponse {
        return serviceRick.getCharacterApi()
    }
}