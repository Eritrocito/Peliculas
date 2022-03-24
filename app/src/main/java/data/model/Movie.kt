package data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//Acá va el código que llama al server para traer la info, mediante retrofit.
// Este el modelo del objeto Pelicula, cuyos atributos coinciden con lo que devuelve la API
data class Movie(
    val id: Int = -1,
    val adult: Boolean = false,
    //val genre_ids: List<Int> = listOf(), //Inicializo como lista vacía
    val backdrop_path: String = "",
    val original_title: String = "",
    val original_language: String = "",
    val overview: String = "",
    val popularity: Double = -1.0,
    val poster_path: String = "",
    val release_date: String = "",
    val title: String = "",
    val video: Boolean = false,
    val vote_average: Double = -1.0,
    val vote_count: Int = -1,
    val movie_type: String = ""


)

data class MovieList(val results: List<Movie> = listOf())

//La llamada a la API me devuelve una lista de objetos JSON (que serían los objetos Movie), llamada results; por eso,
//la variable results en la clase MovieList. Esto se ve en la documentación de la API.


//Room
@Entity
data class MovieEntity(
    @PrimaryKey
    val id: Int = -1,
    @ColumnInfo(name = "adult")
    val adult: Boolean = false,
    //val genre_ids: List<Int> = listOf(), Room no soporta listas en los campos
    @ColumnInfo(name = "backdrop_path")
    val backdrop_path: String = "",
    @ColumnInfo(name = "original_title")
    val original_title: String = "",
    @ColumnInfo(name = "original_language")
    val original_language: String = "",
    @ColumnInfo(name = "overview")
    val overview: String = "",
    @ColumnInfo(name = "popularity")
    val popularity: Double = -1.0,
    @ColumnInfo(name = "poster_path")
    val poster_path: String = "",
    @ColumnInfo(name = "release_date")
    val release_date: String = "",
    @ColumnInfo(name = "title")
    val title: String = "",
    @ColumnInfo(name = "video")
    val video: Boolean = false,
    @ColumnInfo(name = "vote_average")
    val vote_average: Double = -1.0,
    @ColumnInfo(name = "vote_count")
    val vote_count: Int = -1,
    @ColumnInfo(name = "movie_type")
    val movie_type: String = ""


)

//Movie se usa para traer la info del servidor, mientras que MovieEntity se usa para almacenar esa info en la BD local


//Ahora genero una extension function para transformar una List<MovieEntity> en una MovieList
fun List<MovieEntity>.toMovieList(): MovieList {
    val resultList = mutableListOf<Movie>()
    this.forEach { movieEntity ->
        resultList.add(movieEntity.toMovie())

    }
    return MovieList(resultList)
}

fun MovieEntity.toMovie(): Movie = Movie(
    this.id, //this hace referencia al MovieEntity
    this.adult,
    this.backdrop_path,
    this.original_title,
    this.original_language,
    this.overview,
    this.popularity,
    this.poster_path,
    this.release_date,
    this.title,
    this.video,
    this.vote_average,
    this.vote_count,
    this.movie_type
)

fun Movie.toMovieEntity(movieType: String): MovieEntity = MovieEntity(
    this.id, //this hace referencia al MovieEntity
    this.adult,
    this.backdrop_path,
    this.original_title,
    this.original_language,
    this.overview,
    this.popularity,
    this.poster_path,
    this.release_date,
    this.title,
    this.video,
    this.vote_average,
    this.vote_count,
    movie_type = movieType
)

@Entity
data class FavEntity(
    @PrimaryKey
    val id: Int = -1
)