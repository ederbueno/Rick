package com.example.rickmortyktaula.network

import com.example.rickmortyktaula.model.CharacterResponse
import com.example.rickmortyktaula.modelMovies.MovieConfiguration
import com.example.rickmortyktaula.modelMovies.UpcomingMoviesPage
import retrofit2.http.GET
import retrofit2.http.Query

interface EndPointApi {
    @GET("configuration")
    suspend fun getMoviesConfiguration() : MovieConfiguration

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(@Query("page") page: Int) : UpcomingMoviesPage

}