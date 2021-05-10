package mx.com.disoftware.movieconpendium.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import mx.com.disoftware.movieconpendium.core.Resource
import mx.com.disoftware.movieconpendium.repository.MovieRepository
import java.lang.Exception

/**
 * En la documentacion se especifica que no debemos colocar al ViewModel inyectandola por constructor,
 * para ello nos permite crear nuestro propia forma de crear la dependencia.
 * */
class MovieViewModel(private val repo: MovieRepository) : ViewModel() {
    /**
     * Este método se utiliza para traer las películas más recientes, como se hace uso de corrutinas
     * (suspend, en la clase MovieDataSource) hay que asignar un hilo a la corrutina, el utilizado
     * para traer información remota se utiliza IO.
     */
    fun fetchMainScreenMovies() = liveData(Dispatchers.IO) {
        emit(Resource.Loanding()) // estado: buscando datos (se activa el loading en la interfaz grafica).
        try {
            // Aquí se ejecuta la corrutina para traer los datos del serivicio remoto.
            // Realiza las tres peticiones al mismo tiempo, se pueden hacer para n llamadas, buscar la clase NTuple...
            emit(Resource.Success(
                Triple(repo.getUpcomingMovies(), repo.getTopRatedMovies(), repo.getPopularMovies())) // datos recuperados
            )
        } catch (e: Exception) {
            emit(Resource.Failure(e)) // si existe algun error, no se pudo recuperar los datos
        }
    }

}

class MovieViewModelFactory(private val repo: MovieRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(MovieRepository::class.java).newInstance(repo)
    }
}