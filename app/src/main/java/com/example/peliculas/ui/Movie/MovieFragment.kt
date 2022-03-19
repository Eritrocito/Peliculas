package com.example.peliculas.ui.Movie

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import com.example.peliculas.R
import com.example.peliculas.databinding.FragmentMovieBinding
import com.example.peliculas.ui.adapters.MovieAdapter
import com.example.peliculas.ui.adapters.concat.PopularConcatAdapter
import com.example.peliculas.ui.adapters.concat.TopRatedConcatAdapter
import com.example.peliculas.ui.adapters.concat.UpcomingConcatAdapter
import core.Resource
import data.model.Movie
import data.remote.RemoteMovieDataSource
import presentation.MovieViewModel
import presentation.MovieViewModelFactory
import repository.MovieRepositoryImpl
import repository.RetrofitClient


class MovieFragment : Fragment(R.layout.fragment_movie), MovieAdapter.OnMovieClickListener {

    private lateinit var binding: FragmentMovieBinding //Esta clase me permite hacer referencia a los elementos de este layout
    private val viewModel by viewModels<MovieViewModel> {
        MovieViewModelFactory(
            MovieRepositoryImpl(
                RemoteMovieDataSource(RetrofitClient.webservice)  //Inyección de dependencias manual
            )
        )
    }
    //viewModels se usa para generar la instancia de MovieViewModel usando su Factory
    // Un repo puede tener una interfaz y más de una implementación (ej. para buscar info local y remota); por eso, a viewModels<>
    //tengo que decirle qué implementación voy a usar (en este caso, MovieRepositoryImpl), ya que en el Factory usé la interfaz



    private lateinit var concatAdapter: ConcatAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieBinding.bind(view)
        //binding.progressBar.visibility=View.GONE
        //Esto me permite no usar el FindViewById(...)

        concatAdapter = ConcatAdapter()



        viewModel.fetchMainScreenMovies().observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE

                }

                is Resource.Succes -> {
                    binding.progressBar.visibility = View.GONE
                    concatAdapter.apply {
                        addAdapter(
                            0,
                            UpcomingConcatAdapter(
                                MovieAdapter(
                                    result.data.first.results,
                                    this@MovieFragment
                                )
                            )
                        )
                        addAdapter(
                            0,
                            TopRatedConcatAdapter(
                                MovieAdapter(
                                    result.data.second.results,
                                    this@MovieFragment
                                )
                            )
                        )
                        addAdapter(
                            0,
                            PopularConcatAdapter(
                                MovieAdapter(
                                    result.data.third.results,
                                    this@MovieFragment
                                )
                            )
                        )
                    }
                    //Log.d("LiveData", "Upcoming ${result.data.first} \n \n TopRated: ${result.data.second} \n \n Popular: ${result.data.third}")
                    binding.rvMovies.adapter = concatAdapter
                }

                is Resource.Failure -> {
                    binding.progressBar.visibility = View.GONE
                    Log.d("Error", "${result.exception}")

                }
            }
        })
        /*
       viewModel.fetchTopRatedMovies().observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Loading -> {
                    Log.d("LiveData", "Loading... ")
                }
                is Resource.Succes -> {
                    Log.d("LiveData", "${result.data}")
                }
                is Resource.Failure -> {
                    Log.d("Error", "${result.exception}")
                }
            }
        })*/
    }

    override fun onMovieClick(movie: Movie) {
        //Log.d("Movie", "onMovieClick: $movie")
        val action = MovieFragmentDirections.actionMovieFragmentToMovieDetailFragment(
            movie.poster_path,
            movie.backdrop_path,
            movie.vote_average.toFloat(),
            movie.vote_count,
            movie.overview,
            movie.title,
            movie.original_language,
            movie.release_date
        )
        findNavController().navigate(action)
    }


}