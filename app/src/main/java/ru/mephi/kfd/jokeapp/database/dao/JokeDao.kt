package ru.mephi.kfd.jokeapp.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.mephi.kfd.jokeapp.database.entity.JokeEntity

@Dao
interface JokeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(jokes: List<JokeEntity>)

    @Query("SELECT * FROM jokeentity ORDER BY votes DESC LIMIT 5 OFFSET :page * 5")
    fun getPageJokesBy(page: Int): List<JokeEntity>

    @Query("SELECT COUNT(*) FROM jokeentity")
    fun getTotalCount(): Int
}