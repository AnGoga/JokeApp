package ru.mephi.kfd.jokeapp.remote.types

enum class JokeRequestCategory(val path: String) {
    LATEST("latest"), TOP("top"), RANDOM("random")
}