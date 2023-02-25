package ru.mephi.kfd.jokeapp.ui.screens.joke

import io.ktor.client.call.*
import ru.mephi.kfd.jokeapp.database.AppDatabase
import ru.mephi.kfd.jokeapp.model.PageJoke
import ru.mephi.kfd.jokeapp.remote.service.HttpService
import ru.mephi.kfd.jokeapp.remote.types.JokeRequestCategory
import ru.mephi.kfd.jokeapp.remote.model.Result

class JokeRepository(
    private val httpService: HttpService,
    private val database: AppDatabase
) {

    suspend fun getJokesByCategory(category: JokeRequestCategory, page: Int = 0): Result<PageJoke> {
        try {
            val data = httpService.getPageOfJokes(category.path, page).body<PageJoke>()
            database.getJokeDao().insertAll(data.result.map { it.toEntity() })
            return Result.Success(data)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return try {
            val data = database.getJokeDao().getPageJokesBy(page).map { it.toJoke() }
            val total = database.getJokeDao().getTotalCount()
            println("FROM DB: $data")
            Result.Success(PageJoke(data, total))
        } catch (e: Exception) {
            e.printStackTrace()
            Result.Error(e)
        }
    }
}