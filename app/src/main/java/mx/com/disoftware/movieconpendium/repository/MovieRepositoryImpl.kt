package mx.com.disoftware.movieconpendium.repository

import mx.com.disoftware.movieconpendium.core.InternetChack
import mx.com.disoftware.movieconpendium.ui.data.local.LocalMovieDataSource
import mx.com.disoftware.movieconpendium.ui.data.model.MovieList
import mx.com.disoftware.movieconpendium.ui.data.model.toMovieEntity
import mx.com.disoftware.movieconpendium.ui.data.remote.RemoteMovieDataSource

class MovieRepositoryImpl(
    private val dataSourceRemote: RemoteMovieDataSource,
    private val dataSourceLocal: LocalMovieDataSource
) : MovieRepository {

    override suspend fun getUpcomingMovies(): MovieList {
        return if (InternetChack.isNetworkAvailable()) {
            dataSourceRemote.getUpcomingMovies().results.forEach { movie ->
                dataSourceLocal.saveMovie(movie.toMovieEntity("upcoming"))
            }
            dataSourceLocal.getUpcomingMovies()
        } else {
            dataSourceLocal.getUpcomingMovies()
        }

    }

    override suspend fun getTopRatedMovies(): MovieList {
        return if (InternetChack.isNetworkAvailable()) {
            dataSourceRemote.getTopRatedMovies().results.forEach { movie ->
                dataSourceLocal.saveMovie(movie.toMovieEntity("toprated"))
            }
            dataSourceLocal.getTopRatedMovies()
        } else {
            dataSourceLocal.getTopRatedMovies()
        }

    }

    override suspend fun getPopularMovies(): MovieList {
        return if (InternetChack.isNetworkAvailable()) {
            dataSourceRemote.getPopularMovies().results.forEach { movie ->
                dataSourceLocal.saveMovie(movie.toMovieEntity("popular"))
            }
            dataSourceLocal.getPopularMovies()
        } else
            dataSourceLocal.getPopularMovies()
    }
}