package mx.com.disoftware.movieconpendium.repository

import mx.com.disoftware.movieconpendium.data.remote.MovieDataSource

class MovieRepositoryImpl(private val dataSource: MovieDataSource) : MovieRepository {

    override suspend fun getUpcomingMovies() = dataSource.getUpcomingMovies()

    override suspend fun getTopRatedMovies() = dataSource.getTopRatedMovies()

    override suspend fun getPopularMovies() = dataSource.getPopularMovies()

}