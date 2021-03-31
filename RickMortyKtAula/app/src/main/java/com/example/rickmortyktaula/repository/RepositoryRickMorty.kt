package com.example.rickmortyktaula.repository

import com.example.rickmortyktaula.network.EndPointApi
import com.example.rickmortyktaula.network.RetrofitInit


class RepositoryRickMorty{

    companion object{
        const val chave = "1f54bd990f1cdfb230adb312546d765d"
    }

    private var url = "https://api.themoviedb.org/3/"
    private var service = EndPointApi::class

    private val serviceRick = RetrofitInit(url).create(service)

    suspend fun getConfiguration() = serviceRick.getMoviesConfiguration()

    suspend fun getUpcomingMovies(page: Int = 1) = serviceRick.getUpcomingMovies(page)
}