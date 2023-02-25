package ru.mephi.kfd.jokeapp.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.mephi.kfd.jokeapp.model.Joke

@Entity
class JokeEntity(
    @PrimaryKey var id: Int,
    @ColumnInfo(name = "description") var description: String,
    @ColumnInfo(name = "votes") var votes: Int,
    @ColumnInfo(name = "author") var author: String,
    @ColumnInfo(name = "date") var date: String,
    @ColumnInfo(name = "gifURL") var gifURL: String? = null,
    @ColumnInfo(name = "videoURL") var videoURL: String? = null,
    @ColumnInfo(name = "previewURL") var previewURL: String? = null,
    @ColumnInfo(name = "type") var type: String,
) {
    fun toJoke(): Joke {
        return Joke(id, description, votes, author, date, gifURL, videoURL, previewURL, type)
    }
}