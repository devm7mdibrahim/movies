package com.devm7mdibrahim.movies.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.devm7mdibrahim.movies.data.model.movies.Movie
import com.devm7mdibrahim.movies.databinding.ItemMovieBinding

class MovieAdapter (private val listener: (Movie) -> Unit) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var list = mutableListOf<Movie>()

    inner class MovieViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.movie = movie
            binding.executePendingBindings()

            itemView.setOnClickListener {
                listener(movie)
            }
        }
    }

    fun submitList(list: List<Movie>) {
        this.list.clear()
        this.list.addAll(list)
        notifyItemRangeInserted(0, list.size - 1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(ItemMovieBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(list[position])
    }
}