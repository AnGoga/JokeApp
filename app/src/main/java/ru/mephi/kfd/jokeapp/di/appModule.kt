package ru.mephi.kfd.jokeapp.di

import androidx.room.Room
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.mephi.kfd.jokeapp.database.AppDatabase
import ru.mephi.kfd.jokeapp.remote.service.HttpService
import ru.mephi.kfd.jokeapp.remote.service.impl.HttpServiceImpl
import ru.mephi.kfd.jokeapp.ui.screens.joke.JokeRepository
import ru.mephi.kfd.jokeapp.ui.screens.joke.JokeViewModel


val networkModule = module {
    single { HttpClient(Android) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                prettyPrint = true
            })
        }
    } }

    single { HttpServiceImpl(get()) } bind HttpService::class
}

val databaseModule = module {
    single { Room.databaseBuilder(get(), AppDatabase::class.java, "JokeAppDatabase").build() }
}

val viewModelModule = module {
    viewModel { JokeViewModel(get()) }
}

val appModule = module {
    single { JokeRepository(get(), get()) }

} + networkModule + viewModelModule + databaseModule
