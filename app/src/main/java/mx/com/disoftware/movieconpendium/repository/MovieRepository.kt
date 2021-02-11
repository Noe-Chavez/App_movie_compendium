package mx.com.disoftware.movieconpendium.repository

import mx.com.disoftware.movieconpendium.data.model.MovieList

/**
 * Esta interfaz se encarga de trare la infromación de la db local.
 * Es por esto que se necesita el manejo de call backs (llamadas en segundo plano),
 * En este caso se utilizarán corrutines (suspención de rutinas hasta que se obtiene una
 * respuesta), para ello se usa el keyword suspend.
 */
interface MovieRepository {
    suspend fun getUpcomingMovies(): MovieList
    suspend fun getTopRatedMovies(): MovieList
    suspend fun getPopularMovies(): MovieList
}