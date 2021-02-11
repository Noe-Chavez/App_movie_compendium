package mx.com.disoftware.movieconpendium.data.remote

import mx.com.disoftware.movieconpendium.data.model.MovieList

/*
* Se usa para ir a traer la info.
* */
class MovieDataSource {
    /*
        Nota: se pueden utilizar sin pasar nada en el cosntrutor de MovieList ya que se inicializó
        e model en la clase interna data class MovieList(val results: List<Movie> = listOf()),
        dentro de Movie.
     */
    fun getUpcomingMovies() = MovieList()

    fun getTopRatedMovies() = MovieList()

    fun getPopularMovies() = MovieList()

}