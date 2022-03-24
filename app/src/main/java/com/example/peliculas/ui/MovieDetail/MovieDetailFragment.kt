package com.example.peliculas.ui.MovieDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.peliculas.R
import com.example.peliculas.databinding.FragmentMovieDetailBinding
import data.local.AppDatabase
import data.local.FavDatabase
import data.local.LocalFavDataSource
import data.local.LocalMovieDataSource
import data.model.FavEntity
import data.remote.RemoteMovieDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import presentation.FavViewModel
import presentation.FavViewModelFactory
import presentation.MovieViewModel
import presentation.MovieViewModelFactory
import repository.FavRepository
import repository.MovieRepositoryImpl
import repository.RetrofitClient

class MovieDetailFragment : Fragment(R.layout.fragment_movie_detail) {

    private lateinit var binding: FragmentMovieDetailBinding
    private val args by navArgs<MovieDetailFragmentArgs>()

    private val FavviewModel by viewModels<FavViewModel> {
        FavViewModelFactory(
            FavRepository(LocalFavDataSource(FavDatabase.getDatabase(requireContext()).favDao()))
        )
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieDetailBinding.bind(view)

        Glide.with(requireContext()).load("https://image.tmdb.org/t/p/w500/${args.posterImageUrl}")
            .centerCrop().into(binding.imgMovie)
        Glide.with(requireContext())
            .load("https://image.tmdb.org/t/p/w500/${args.backgroundImageUrl}").centerCrop()
            .into(binding.imgBackground)
        binding.txtDescription.text = args.overview
        binding.txtMovieTitle.text = args.title
        binding.txtLanguage.text = "Language ${args.language}"
        binding.txtReleased.text = "Released ${args.releaseDate}"
        binding.txtRating.text = "${args.voteAverage} ${args.voteCount} Reviews"



        binding.btnFav.setOnClickListener {

            FavviewModel.saveFavId(FavEntity(args.id))
        }

    }


}


