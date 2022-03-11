package data.remote

import data.model.MovieList

//Contiene a los métodos que van a buscar la info al server

class MovieDataSource {

    fun getUpcomingMovies(): MovieList {
        return MovieList()
    }

    fun getTopRatedMovies(): MovieList {
        return MovieList()
    }

    fun getPopularMovies(): MovieList {
        return MovieList()
    }
}