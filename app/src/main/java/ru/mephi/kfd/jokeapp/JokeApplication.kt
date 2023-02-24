package ru.mephi.kfd.jokeapp

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.Koin
import org.koin.core.context.startKoin
import ru.mephi.kfd.jokeapp.di.appModule

class JokeApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@JokeApplication)
            modules(appModule)
        }
    }
}