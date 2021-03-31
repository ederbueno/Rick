package com.example.rickmortyktaula.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickmortyktaula.modelMovies.Movie
import com.example.rickmortyktaula.modelMovies.UpcomingMoviesPage
import com.example.rickmortyktaula.repository.RepositoryRickMorty
import com.example.rickmortyktaula.repository.SingletonConfiguration
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.UnknownHostException

class CharacterViewModel : ViewModel() {

    val moviesList = MutableLiveData<List<Movie>>()
    val errorMessage = MutableLiveData<String>()
    val nextPageLoading by lazy { MutableLiveData<Boolean>() }
    val firstPageLoading by lazy { MutableLiveData<Boolean>() }
    var nextPage = 0

    private val repository = RepositoryRickMorty()

    init {
        getConfiguration()
    }

    fun getConfiguration() = CoroutineScope(IO).launch {
        try {
            firstPageLoading.postValue(true)
            repository.getConfiguration().let { configuration ->
                SingletonConfiguration.setConfiguration(configuration)

                getUpcomingMovies()
            }
        } catch (error: Throwable) {
            safeApi(error, errorMessage)
        }finally {
            firstPageLoading.postValue(false)
        }
    }

    fun getUpcomingMovies() = CoroutineScope(IO).launch {
        try {
            repository.getUpcomingMovies().let { moviesPage ->
                updateNextPage(moviesPage)
                moviesList.postValue(moviesPage.movies.map { it.copy(title = "${it.title} pagina ${moviesPage.page}") })
            }
        } catch (error: Throwable) {
            safeApi(error, errorMessage)
        }
    }

    private fun updateNextPage(moviesPage: UpcomingMoviesPage) {
        nextPage = moviesPage.page?.plus(1) ?: 0
    }

    fun requestNextPage() = CoroutineScope(IO).launch {
        try {
            nextPageLoading.postValue(true)
            repository.getUpcomingMovies(nextPage).let { moviesPage ->
                updateNextPage(moviesPage)
                moviesList.postValue(moviesPage.movies.map { it.copy(title = "${it.title} pagina ${moviesPage.page}") })
            }
        } catch (error: Throwable) {
            safeApi(error, errorMessage)
        } finally {
            nextPageLoading.postValue(false)
        }
    }
}

fun safeApi(error: Throwable, errorMessage: MutableLiveData<String>) {
    when (error) {
        is HttpException -> errorMessage.postValue("Erro de conexão código: ${error.code()}")
        is UnknownHostException -> errorMessage.postValue("Verifique sua conexão")
    }
}