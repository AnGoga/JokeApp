package ru.mephi.kfd.jokeapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.mephi.kfd.jokeapp.database.dao.JokeDao
import ru.mephi.kfd.jokeapp.database.entity.JokeEntity
import ru.mephi.kfd.jokeapp.model.Joke

@Database(entities = [JokeEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getJokeDaoDao(): JokeDao
}