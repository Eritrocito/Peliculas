package presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import data.model.FavEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import repository.FavRepository
import repository.MovieRepository

class FavViewModel(private val favRepo: FavRepository) : ViewModel() {
    fun saveFavId(favEntity: FavEntity) = CoroutineScope(Dispatchers.IO).launch {
        favRepo.saveFavID(favEntity)
    }
}

class FavViewModelFactory (private val repo: FavRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T { //create ya no acepta nullables (por eso le saco el ?)
        return modelClass.getConstructor(FavRepository::class.java).newInstance(repo)
    }
}