package ru.mephi.kfd.jokeapp.remote.service

import io.ktor.client.statement.*

interface HttpService {
    suspend fun getPageOfJokes(category: String, page: Int): HttpResponse
    suspend fun getRandomJoke(category: String): HttpResponse
}