package ru.mephi.kfd.jokeapp.ui.screens.joke

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import ru.mephi.kfd.jokeapp.databinding.ActivityJokeBinding
import ru.mephi.kfd.jokeapp.model.PageJoke
import ru.mephi.kfd.jokeapp.remote.model.Result
import org.koin.androidx.viewmodel.ext.android.viewModel


class JokeActivity : AppCompatActivity() {
    private val binding by lazy { ActivityJokeBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}