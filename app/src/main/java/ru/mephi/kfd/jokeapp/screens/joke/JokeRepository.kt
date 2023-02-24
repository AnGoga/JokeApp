package ru.mephi.kfd.jokeapp.screens.joke

import io.ktor.client.call.*
import ru.mephi.kfd.jokeapp.database.AppDatabase
import ru.mephi.kfd.jokeapp.model.PageJoke
import ru.mephi.kfd.jokeapp.remote.model.Result
import ru.mephi.kfd.jokeapp.remote.service.HttpService
import ru.mephi.kfd.jokeapp.remote.types.JokeRequestCategory
import java.io.IOException

class JokeRepository(
    private val service: HttpService,
    private val database: AppDatabase
) {

    suspend fun getJokes(category: JokeRequestCategory, page: Int = 0): Result<PageJoke> {
        try {
            val response = service.getPageOfJokes(category.path, page)
            if (response.status.value == 200) {
                val data = response.body<PageJoke>()
                database.getJokeDaoDao().insertAll(data.result.map { it -> it.toJokeEntity() })
                return Result.Success(data)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        try {
            val data = database.getJokeDaoDao().getPageByRating(page).map { it.toJoke() }
            return Result.Success<PageJoke>(PageJoke(data, -1))
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return Result.Error(IOException())

    }

}