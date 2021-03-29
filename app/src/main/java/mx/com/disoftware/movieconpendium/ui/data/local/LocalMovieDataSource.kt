package mx.com.disoftware.movieconpendium.ui.data.local

import mx.com.disoftware.movieconpendium.ui.data.model.MovieEntity
import mx.com.disoftware.movieconpendium.ui.data.model.toMovieList

class LocalMovieDataSource(private val movieDao: MovieDao) {

    suspend fun getUpcomingMovies() =
        movieDao.getAllMovies().filter { it.movie_type == "upcoming" }.toMovieList()

    suspend fun getTopRatedMovies() =
        movieDao.getAllMovies().filter { it.movie_type == "toprated" }.toMovieList()

    suspend fun getPopularMovies() =
        movieDao.getAllMovies().filter { it.movie_type == "popular" }.toMovieList()

    suspend fun saveMovie(movie: MovieEntity) {
        movieDao.saveMovie(movie)
    }

}