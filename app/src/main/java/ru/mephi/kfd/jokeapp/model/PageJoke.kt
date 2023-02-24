package ru.mephi.kfd.jokeapp.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class PageJoke(
    @SerialName("result") var result: List<Joke>,
    @SerialName("totalCount") var totalCount: Int
)