package repository

import data.model.MovieList
import data.remote.RemoteMovieDataSource

//Acá se hace la implementación de los métodos definidos en la interfaz

class MovieRepositoryImpl(private val dataSourceRemote: RemoteMovieDataSource) : MovieRepository {
    override suspend fun getUpcomingMovies(): MovieList = dataSourceRemote.getUpcomingMovies()
    //Al implementar un método de una interfaz, sí o sí hay que hacer override
    override suspend fun getTopRatedMovies(): MovieList = dataSourceRemote.getTopRatedMovies()

    override suspend fun getPopularMovies(): MovieList = dataSourceRemote.getPopularMovies()

}