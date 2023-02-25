package ru.mephi.kfd.jokeapp.remote.service.impl

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import ru.mephi.kfd.jokeapp.remote.service.HttpService

class HttpServiceImpl(
    private val client: HttpClient
) : HttpService {

    override suspend fun getRandomJoke(category: String)
        = client.get("$RANDOM_URL?json=true")

    override suspend fun getPageOfJokes(category: String, page: Int)
        = client.get("$BASE_URL/$category/$page?json=true")

    companion object {
        private const val BASE_URL = "https://developerslife.ru"
        private const val RANDOM_URL = "https://developerslife.ru/random?json=true"
    }
}