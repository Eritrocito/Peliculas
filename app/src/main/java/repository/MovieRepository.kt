package repository

import data.model.MovieList

//En la interfaz solamente se definen los métodos (los que se van a usar en el datasource),
//pero la implementación es en MovieRepositoryImpl

interface MovieRepository {
    suspend fun getUpcomingMovies(): MovieList //Implementado como corutina, que se suspende hasta que el server responde
    suspend fun getTopRatedMovies(): MovieList
    suspend fun getPopularMovies(): MovieList
}