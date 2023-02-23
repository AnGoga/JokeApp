package ru.mephi.kfd.jokeapp.model

import kotlinx.serialization.Serializable

@Serializable
class PageJoke(
    var result: List<Joke>,
    var totalCount: Int
)