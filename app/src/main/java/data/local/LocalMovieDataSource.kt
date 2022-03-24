package data.local

import application.AppConstants
import data.model.FavEntity
import data.model.MovieEntity
import data.model.MovieList
import data.model.toMovieList

class LocalMovieDataSource(private val movieDao: MovieDao) {
    suspend fun getUpcomingMovies(): MovieList {
        return movieDao.getAllMovies().filter { it.movie_type == "upcoming" }.toMovieList()
    }

    suspend fun getTopRatedMovies(): MovieList {
        return movieDao.getAllMovies().filter { it.movie_type == "toprated" }.toMovieList()
    }

    suspend fun getPopularMovies(): MovieList {
        return movieDao.getAllMovies().filter { it.movie_type == "popular" }.toMovieList()
    }

    suspend fun saveMovie(movie: MovieEntity) {
        movieDao.saveMovie(movie)
    }
}

class LocalFavDataSource(private val favDao: FavDao) {

    //suspend fun getFavId(movie: MovieEntity) {
      //  movieDao.saveMovie(movie)
    //}
    suspend fun saveFavID(favEntity: FavEntity) {
        favDao.saveFavId(favEntity)
    }

}