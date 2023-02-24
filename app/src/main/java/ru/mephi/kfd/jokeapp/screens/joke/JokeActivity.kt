package ru.mephi.kfd.jokeapp.screens.joke

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
import ru.mephi.kfd.jokeapp.remote.types.JokeRequestCategory

class JokeActivity : AppCompatActivity() {
    private val binding by lazy { ActivityJokeBinding.inflate(layoutInflater) }
    private val viewModel: JokeViewModel by viewModel()
    private val adapter by lazy { JokeAdapter(this) }
    private var page = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initRecyclerView()
        initOnClick()
        subscribe()
    }

    private fun subscribe() {
        lifecycleScope.launchWhenCreated {
            viewModel.flow.collect {
                when(it) {
                    is Result.Success -> {
                        updateRecyclerView(it.data)
                    }
                    else -> Toast.makeText(this@JokeActivity, "Что-то пошло не так", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun updateRecyclerView(data: PageJoke) {
        adapter.update(data)
    }

    private fun initOnClick() {
        binding.typeGroup.setOnCheckedChangeListener { radioGroup, i ->
            page = 0
            when(radioGroup.checkedRadioButtonId) {
                binding.top.id -> viewModel.getJokes(JokeRequestCategory.TOP, page)
                binding.last.id -> showInDevelopingToast()
                binding.random.id -> showInDevelopingToast()
            }
        }
        binding.nextBtn.setOnClickListener {
            when(binding.typeGroup.checkedRadioButtonId) {
                binding.top.id -> viewModel.getJokes(JokeRequestCategory.TOP, ++page)
                binding.last.id -> showInDevelopingToast()
                binding.random.id -> showInDevelopingToast()
            }
        }
        binding.previousBtn.setOnClickListener {
            when(binding.typeGroup.checkedRadioButtonId) {
                binding.top.id -> viewModel.getJokes(JokeRequestCategory.TOP, --page)
                binding.last.id -> showInDevelopingToast()
                binding.random.id -> showInDevelopingToast()
            }
        }
    }

    private fun showInDevelopingToast() {
        Toast.makeText(this, "В разработке", Toast.LENGTH_LONG).show()
    }

    private fun initRecyclerView() {
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }
}