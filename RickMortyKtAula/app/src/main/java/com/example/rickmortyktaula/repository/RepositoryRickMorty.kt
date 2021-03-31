package com.example.rickmortyktaula.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.liveData
import com.example.rickmortyktaula.model.CharacterResponse
import com.example.rickmortyktaula.network.EndPointApi
import com.example.rickmortyktaula.network.RetrofitInit
import com.example.rickmortyktaula.network.UpcommingMoviesPageSource


class RepositoryRickMorty{

    companion object{
        const val chave = "1f54bd990f1cdfb230adb312546d765d"
    }

    private var url = "https://api.themoviedb.org/3/"
    private var service = EndPointApi::class

    private val serviceRick = RetrofitInit(url).create(service)

    suspend fun getConfiguration() = serviceRick.getMoviesConfiguration()

    fun getUpcomingMovies() = Pager(PagingConfig(pageSize = 20)){
        UpcommingMoviesPageSource(url)
    }.flow
}