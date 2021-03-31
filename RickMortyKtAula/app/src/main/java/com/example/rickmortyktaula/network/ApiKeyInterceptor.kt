package com.example.rickmortyktaula.network

import com.example.rickmortyktaula.repository.RepositoryRickMorty
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

//add API KEY to each request
class ApiKeyInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest: Request = chain.request()
        val originalUrl: HttpUrl = originalRequest.url
        val newUrl: HttpUrl = originalUrl.newBuilder()
            .addQueryParameter("api_key", RepositoryRickMorty.chave)
            .build()
        val requestBuilder: Request.Builder = originalRequest.newBuilder()
            .url(newUrl)
        val request: Request = requestBuilder.build()
        return chain.proceed(request)
    }
}