package repository

import application.AppConstants
import com.google.gson.GsonBuilder
import data.model.MovieList
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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

object RetrofitClient{
    val webservice by lazy{
        Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create())) //Para convertir la info en JSON
            .build().create(WebService::class.java)
    }

}