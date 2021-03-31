package com.example.rickmortyktaula.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickmortyktaula.modelMovies.Movie

class UpcommingMoviesPageSource(url: String) : PagingSource<Int, Movie>() {
    private val service = RetrofitInit(url).create(EndPointApi::class)

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val nextPage = params.key ?: 1
            val response = service.getUpcomingMovies(nextPage)
            LoadResult.Page(
                data = response.movies,
                prevKey = null,
                nextKey = response.page?.plus(1)
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }
}