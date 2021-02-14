package mx.com.disoftware.movieconpendium.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import mx.com.disoftware.movieconpendium.core.Resource
import mx.com.disoftware.movieconpendium.repository.MovieRepository
import java.lang.Exception

/**
 * En la documentacion se especifica que no debemos colocar al ViewModel generando la instancia,
 * para ello nos permite crear nuestro propia forma de crear la dependencia.
 * */
class MovieViewModel(private val repo: MovieRepository) : ViewModel() {
    /**
     * Este método se utiliza para traer las películas más recientes, como se hace uso de corrutinas
     * (suspend, en la clase MovieDataSource) hay que asignar un hilo a la corrutina, el utilizado
     * para traer información remota se utiliza IO.
     */
    fun fetchUpComingMovies() = liveData(Dispatchers.IO) {
        emit(Resource.Loanding())
        try {
            emit(Resource.Success(repo.getUpcomingMovies()))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }

    fun fetchUpPopularMovies() = liveData(Dispatchers.IO) {
        emit(Resource.Loanding())
        try {
            emit(Resource.Success(repo.getPopularMovies()))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }

    fun fetchUpRatedMovies() = liveData(Dispatchers.IO) {
        emit(Resource.Loanding())
        try {
            emit(Resource.Success(repo.getTopRatedMovies()))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }

}

class MovieViewModelFactory(private val repo: MovieRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(MovieRepository::class.java).newInstance(repo)
    }
}