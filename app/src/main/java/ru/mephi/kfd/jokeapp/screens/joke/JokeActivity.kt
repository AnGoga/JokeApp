package ru.mephi.kfd.jokeapp.screens.joke

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.mephi.kfd.jokeapp.R
import ru.mephi.kfd.jokeapp.databinding.ActivityJokeBinding

class JokeActivity : AppCompatActivity() {
    private val binding by lazy { ActivityJokeBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}