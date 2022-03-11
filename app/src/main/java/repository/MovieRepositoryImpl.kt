package repository

import data.model.MovieList

//Acá se hace la implementación de los métodos definidos en la interfaz

class MovieRepositoryImpl: MovieRepository {
    override suspend fun getUpcomingMovies(): MovieList {
        TODO("Not yet implemented")
    }

    override suspend fun getTopRatedMovies(): MovieList {
        TODO("Not yet implemented")
    }

    override suspend fun getPopularMovies(): MovieList {
        TODO("Not yet implemented")
    }

}