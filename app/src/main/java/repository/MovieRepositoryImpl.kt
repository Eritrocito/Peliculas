package repository

import android.util.Log
import core.InternetCheck
import data.local.LocalFavDataSource
import data.local.LocalMovieDataSource
import data.model.FavEntity
import data.model.MovieList
import data.model.toMovieEntity
import data.remote.RemoteMovieDataSource

//Acá se hace la implementación de los métodos definidos en la interfaz
//Al implementar un método de una interfaz, sí o sí hay que hacer override
class MovieRepositoryImpl(
    private val dataSourceRemote: RemoteMovieDataSource,
    private val dataSourceLocal: LocalMovieDataSource,
) : MovieRepository {
    override suspend fun getUpcomingMovies(): MovieList {
        return if (InternetCheck.isNetworkAvailable()) {
            dataSourceRemote.getUpcomingMovies().results.forEach { movie ->
                dataSourceLocal.saveMovie(movie.toMovieEntity("upcoming"))
                Log.d("Internet", "Hay conexión")
            }
            dataSourceLocal.getUpcomingMovies()
        } else {
            dataSourceLocal.getUpcomingMovies()
           // Log.d("Internet","No hay conexión")
        }
    }


    override suspend fun getTopRatedMovies(): MovieList {
        return if (InternetCheck.isNetworkAvailable()) {
            dataSourceRemote.getTopRatedMovies().results.forEach { movie ->
                dataSourceLocal.saveMovie(movie.toMovieEntity("toprated"))
            }
            dataSourceLocal.getTopRatedMovies()
        } else {
            dataSourceLocal.getTopRatedMovies()
        }
    }

    override suspend fun getPopularMovies(): MovieList {
        return if (InternetCheck.isNetworkAvailable()) {
            dataSourceRemote.getPopularMovies().results.forEach { movie ->
                dataSourceLocal.saveMovie(movie.toMovieEntity("popular"))
            }
            dataSourceLocal.getPopularMovies()
        } else {
            dataSourceLocal.getPopularMovies()
        }
    }
}

class FavRepository(
    private val dataSourceFavLocal: LocalFavDataSource
) {
    suspend fun saveFavID(favEntity: FavEntity) {
        dataSourceFavLocal.saveFavID(favEntity)
    }
}