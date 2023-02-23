package ru.mephi.kfd.jokeapp.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
class Joke(
    @SerialName("id") var id: Int,
    @SerialName("description") var description: String,
    @SerialName("votes") var votes: Int,
    @SerialName("author") var author: String,
    @SerialName("date") var date: String,
    @SerialName("gifURL") var gifURL: String? = null,
    @SerialName("videoURL") var videoURL: String? = null,
    @SerialName("previewURL") var previewURL: String? = null,
    @SerialName("videoPath") var videoPath: String? = null,
    @SerialName("type") var type: String,
) {

    companion object {
        const val TYPE_GIF = "gif"
        const val TYPE_VIDEO = "video"
        const val TYPE_COUB = "coub"
    }
}