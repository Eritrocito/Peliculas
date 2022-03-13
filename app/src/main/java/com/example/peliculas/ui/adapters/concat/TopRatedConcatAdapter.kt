package com.example.peliculas.ui.adapters.concat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.peliculas.databinding.TopRatedMoviesRowsBinding
import com.example.peliculas.databinding.UpcomingMovieRowBinding
import com.example.peliculas.ui.adapters.MovieAdapter
import core.BaseConcatHolder

class TopRatedConcatAdapter(private val moviesAdapter: MovieAdapter): RecyclerView.Adapter<BaseConcatHolder<*>>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseConcatHolder<*> {
        val itemBinding = TopRatedMoviesRowsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ConcatViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: BaseConcatHolder<*>, position: Int) {
        when(holder){
            is ConcatViewHolder -> holder.bind(moviesAdapter)
        }

    }

    override fun getItemCount(): Int =1

    private inner class ConcatViewHolder(val binding: TopRatedMoviesRowsBinding): BaseConcatHolder<MovieAdapter>(binding.root){
        override fun bind(adapter: MovieAdapter) {
            binding.rvTopRatedMovies.adapter= adapter
        }
    }

}