package repository

import data.model.MovieList
import retrofit2.http.GET
import retrofit2.http.Query

//Es el encargado de usar retrofit para traer info del servidor
interface WebService {
    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(@Query("api_key") apiKey: String): MovieList
    //En la url, hay una query con api_key

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(@Query("api_key") apiKey: String): MovieList

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") apiKey: String): MovieList
}

//ctrl+A --> selecciona todoel codigo
//ctral+alt+l --> indenta