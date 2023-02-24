package ru.mephi.kfd.jokeapp.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.mephi.kfd.jokeapp.database.entity.JokeEntity
import ru.mephi.kfd.jokeapp.model.Joke

@Dao
interface JokeDao {
    @Query("SELECT * FROM jokeentity ORDER BY votes DESC LIMIT 5 OFFSET :page * 5 ")
    fun getPageByRating(page: Int): List<JokeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(jokes: List<JokeEntity>)
}