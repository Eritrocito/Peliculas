package com.example.peliculas.ui.Movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.peliculas.R
import com.example.peliculas.databinding.FragmentMovieBinding


class MovieFragment : Fragment(R.layout.fragment_movie) {

    private lateinit var binding: FragmentMovieBinding //Esta clase me permite hacer referencia a los elementos de este layout

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentMovieBinding.bind(view)
        //binding.progressBar.visibility=View.GONE
        //Esto me permite no usar el FindViewById(...)

    }


}