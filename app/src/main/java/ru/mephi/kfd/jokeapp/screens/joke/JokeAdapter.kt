package ru.mephi.kfd.jokeapp.screens.joke

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.mephi.kfd.jokeapp.R
import ru.mephi.kfd.jokeapp.model.Joke
import ru.mephi.kfd.jokeapp.model.Joke.Companion.TYPE_GIF
import ru.mephi.kfd.jokeapp.model.PageJoke

class JokeAdapter(private val context: Context) : RecyclerView.Adapter<JokeAdapter.JokeHolder>() {
    private val inflater = LayoutInflater.from(context)
    private val items: ArrayList<Joke> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokeHolder {
        return JokeHolder(inflater.inflate(R.layout.joke_view, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: JokeHolder, position: Int) {
        holder.initHolder(items[position])
    }

    fun update(data: PageJoke) {
        items.clear()
        items += data.result
        notifyDataSetChanged()
    }

    inner class JokeHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val image: ImageView = itemView.findViewById(R.id.image)
        private val text: TextView = itemView.findViewById(R.id.text)
        private val rating: TextView = itemView.findViewById(R.id.text_rating)
        private val date: TextView = itemView.findViewById(R.id.text_date)

        fun initHolder(joke: Joke) {
            text.text = joke.description
            rating.text = joke.votes.toString()
            date.text = joke.date

            Glide
                .with(this@JokeAdapter.context)
                .load(when(joke.type) {
                    TYPE_GIF -> joke.gifURL
                    else -> joke.previewURL
                }).into(image)
        }
    }
}