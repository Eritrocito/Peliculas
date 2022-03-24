package presentation

import androidx.annotation.NonNull
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import core.Resource
import data.model.FavEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import repository.FavRepository
import repository.MovieRepository
import java.lang.Exception

//Puedo tener un viewmodel por cada vista, pero si son pocas es  mejor tener uno solo

class MovieViewModel(private val repo: MovieRepository) : ViewModel() {
    fun fetchMainScreenMovies() =
        liveData(Dispatchers.IO) {
            //Dispatchers.io es un hilo que se asigna a las corutinas de llamada al servidor
            emit(Resource.Loading()) //Antes de ir a buscar la info, emito un valor de carga

            try {
                emit(
                    Resource.Succes(
                        Triple(
                            repo.getUpcomingMovies(),
                            repo.getTopRatedMovies(),
                            repo.getPopularMovies()
                        )
                    )
                )
                //Se puede hacer para más con Ntuple
                //Esto se ejecuta secuencialmente: primero Upcoming, cuando termina viene TopRated, y después Popular
            } catch (e: Exception) {
                emit(Resource.Failure(e))
            }
        }

        /*
    fun fetchPopularMovies() =
        liveData(Dispatchers.IO) { //Dispatchers.io es un hilo que se asigna a las corutinas de llamada al servidor
            emit(Resource.Loading()) //Antes de ir a buscar la info, emito un valor de carga

            try {
                emit(Resource.Succes(repo.getPopularMovies()))

            } catch (e: Exception) {
                emit(Resource.Failure(e))

            }
        }

    fun fetchTopRatedMovies() =
        liveData(Dispatchers.IO) { //Dispatchers.io es un hilo que se asigna a las corutinas de llamada al servidor
            emit(Resource.Loading()) //Antes de ir a buscar la info, emito un valor de carga

            try {
                emit(Resource.Succes(repo.getTopRatedMovies()))

            } catch (e: Exception) {
                emit(Resource.Failure(e))

            }
        }
}*/
}

class MovieViewModelFactory(private val repo: MovieRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T { //create ya no acepta nullables (por eso le saco el ?)
        return modelClass.getConstructor(MovieRepository::class.java).newInstance(repo)
    }
}
//El ViewModel no permite pasarle nada por constructor, y para eso es que existe el ViewModelFactory