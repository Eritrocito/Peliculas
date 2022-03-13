package data.remote

import application.AppConstants
import data.model.MovieList
import repository.WebService

//Contiene a los métodos que van a buscar la info al server

class MovieDataSource(private val webService: WebService) {

    suspend fun getUpcomingMovies(): MovieList = webService.getUpcomingMovies(AppConstants.API_KEY)
    //return MovieList()

    suspend fun getTopRatedMovies(): MovieList = webService.getTopRatedMovies(AppConstants.API_KEY)

    suspend fun getPopularMovies(): MovieList = webService.getPopularMovies(AppConstants.API_KEY)
}