package ru.mephi.kfd.jokeapp.ui.screens.joke

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import ru.mephi.kfd.jokeapp.model.PageJoke
import ru.mephi.kfd.jokeapp.remote.types.JokeRequestCategory
import ru.mephi.kfd.jokeapp.remote.model.Result

class JokeViewModel(
    private val repository: JokeRepository
) : ViewModel() {

    private val _flow = MutableSharedFlow<Result<PageJoke>>()

    val flow
        get() = _flow as Flow<Result<PageJoke>>


    fun getJokesByCategory(category: JokeRequestCategory, page: Int = 0) {
        viewModelScope.launch(Dispatchers.IO) {
            val data = repository.getJokesByCategory(category, page)
            _flow.emit(data)
        }
    }
}