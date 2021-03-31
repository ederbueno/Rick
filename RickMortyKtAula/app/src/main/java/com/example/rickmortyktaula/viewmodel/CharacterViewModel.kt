package com.example.rickmortyktaula.viewmodel

import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.rickmortyktaula.model.Result
import com.example.rickmortyktaula.modelMovies.Movie
import com.example.rickmortyktaula.modelMovies.MovieConfiguration
import com.example.rickmortyktaula.repository.RepositoryRickMorty
import com.example.rickmortyktaula.repository.SingletonConfiguration
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.flattenMerge
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.UnknownHostException

class CharacterViewModel : ViewModel() {

    val moviesList = MutableLiveData<List<Movie>>()
    val errorMessage = MutableLiveData<String>()

    private val repository = RepositoryRickMorty()

    init {
        getConfiguration()
    }
    fun getConfiguration() = CoroutineScope(IO).launch {
        try {
            repository.getConfiguration().let { configuration ->
                SingletonConfiguration.setConfiguration(configuration)
            }
        }catch (error: Throwable){
            safeApi(error, errorMessage)
        }
    }

    private val clearListCh = Channel<Unit>(Channel.CONFLATED)

    val movies = flowOf(
        clearListCh.receiveAsFlow().map { PagingData.empty() },
        repository.getUpcomingMovies()
            // cachedIn() shares the paging state across multiple consumers of posts,
            // e.g. different generations of UI across rotation config change
            .cachedIn(viewModelScope)
    ).flattenMerge(2)
}

fun safeApi(error: Throwable, errorMessage:  MutableLiveData<String>){
    when(error){
        is HttpException -> errorMessage.postValue("Erro de conexão código: ${error.code()}")
        is UnknownHostException -> errorMessage.postValue("Verifique sua conexão")
    }
}