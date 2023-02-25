package ru.mephi.kfd.jokeapp.ui.screens.joke

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.mephi.kfd.jokeapp.databinding.ActivityJokeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.mephi.kfd.jokeapp.remote.model.Result
import ru.mephi.kfd.jokeapp.remote.types.JokeRequestCategory


class JokeActivity : AppCompatActivity() {
    private val binding by lazy { ActivityJokeBinding.inflate(layoutInflater) }
    private val adapter by lazy { JokeAdapter(this) }
    private val viewModel: JokeViewModel by viewModel()
    private var page = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initRecyclerView()
        subscribe()
        initOnClicks()
    }

    private fun initOnClicks() {
        binding.typeGroup.setOnCheckedChangeListener { radioGroup, i ->
            page = 0
            when(radioGroup.checkedRadioButtonId) {
                binding.top.id -> viewModel.getJokesByCategory(JokeRequestCategory.TOP, page)
                binding.last.id -> showDevelopingToast()
                binding.random.id -> showDevelopingToast()
            }
        }

        binding.nextBtn.setOnClickListener {
            when(binding.typeGroup.checkedRadioButtonId) {
                binding.top.id -> viewModel.getJokesByCategory(JokeRequestCategory.TOP, ++page)
                binding.last.id -> showDevelopingToast()
                binding.random.id -> showDevelopingToast()
            }
        }

        binding.previousBtn.setOnClickListener {
            when(binding.typeGroup.checkedRadioButtonId) {
                binding.top.id -> viewModel.getJokesByCategory(JokeRequestCategory.TOP, --page)
                binding.last.id -> showDevelopingToast()
                binding.random.id -> showDevelopingToast()
            }
        }
    }

    private fun showDevelopingToast() {
        Toast.makeText(this, "В разработке", Toast.LENGTH_LONG).show()
    }

    private fun subscribe() {
        lifecycleScope.launch {
            viewModel.flow.collect { it ->
                when(it) {
                    is Result.Success -> {
                        adapter.updateData(it.data)
                        binding.pageNum.text = "$page/${it.data.totalPages}"
                    }
                    is Result.Error -> {
                        Toast.makeText(this@JokeActivity, "Что-то пошло не так", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private fun initRecyclerView() {
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }
}