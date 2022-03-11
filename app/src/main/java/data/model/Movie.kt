package data.model
//Acá va el código que llama al server para traer la info, mediante retrofit.
// Este el modelo del objeto Pelicula, cuyos atributos coinciden con lo que devuelve la API
data class Movie(
                val id:Int = -1,
                val adult: Boolean = false,
                val genre_ids: List<Int> = listOf(), //Inicializo como lista vacía
                val backdrop_path: String = "",
                val original_title: String = "",
                val original_language: String ="",
                val overview: String ="",
                val popularity: Double = -1.0,
                val poster_path: String="",
                val release_date: String ="",
                val title: String ="",
                val video: Boolean = false,
                val vote_average: Double = -1.0,
                val vote_count: Int = -1


)

data class MovieList (val results: List<Movie> = listOf())
