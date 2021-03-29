package mx.com.disoftware.movieconpendium.ui.data.remote

import mx.com.disoftware.movieconpendium.application.AppConstants
import mx.com.disoftware.movieconpendium.repository.WebService

/*
* Se usa para ir a traer la info.
* */
class RemoteMovieDataSource(private val webService: WebService) {
    /*
        Nota: se pueden utilizar sin pasar nada en el cosntrutor de MovieList ya que se inicializó
        e model en la clase interna data class MovieList(val results: List<Movie> = listOf()),
        dentro de Movie.
     */
    suspend fun getUpcomingMovies() = webService.getUpcomingMovies(AppConstants.API_KEY)

    suspend fun getTopRatedMovies() = webService.getTopRatedMovies(AppConstants.API_KEY)

    suspend fun getPopularMovies() = webService.getPopularMovies(AppConstants.API_KEY)

}