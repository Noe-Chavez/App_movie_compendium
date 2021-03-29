package mx.com.disoftware.movieconpendium.repository

import mx.com.disoftware.movieconpendium.ui.data.remote.RemoteMovieDataSource

class MovieRepositoryImpl(private val dataSourceRemote: RemoteMovieDataSource) : MovieRepository {

    override suspend fun getUpcomingMovies() = dataSourceRemote.getUpcomingMovies()

    override suspend fun getTopRatedMovies() = dataSourceRemote.getTopRatedMovies()

    override suspend fun getPopularMovies() = dataSourceRemote.getPopularMovies()

}