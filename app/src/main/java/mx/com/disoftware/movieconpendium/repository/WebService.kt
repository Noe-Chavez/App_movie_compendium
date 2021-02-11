package mx.com.disoftware.movieconpendium.repository

import com.google.gson.GsonBuilder
import mx.com.disoftware.movieconpendium.application.AppConstants
import mx.com.disoftware.movieconpendium.data.model.MovieList
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Esta interfaz se encarga de trare la infromación de del API.
 * Es por esto que se necesita el manejo de call backs (llamadas en segundo plano),
 * En este caso se utilizarán corrutines (suspención de rutinas hasta que se obtiene una
 * respuesta), para ello se usa el keyword suspend.
 */
interface WebService {

    // https://api.themoviedb.org/3/movie/upcoming?api_key=8cd13f584a4b55e728297b531679c8c8
    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(@Query("api_key") apiKey: String): MovieList // api_key debe ser igual al de la URI

    // https://api.themoviedb.org/3/movie/top_rated?api_key=8cd13f584a4b55e728297b531679c8c8
    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(@Query("api_key") apiKey: String): MovieList

    // https://api.themoviedb.org/3/movie/popular?api_key=8cd13f584a4b55e728297b531679c8c8
    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") apiKey: String): MovieList

}

object RetrofitClient {
    val webService by lazy {
        Retrofit.Builder()
                .baseUrl(AppConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create())) // Convertir de JSon a objeto kotlin.
                .build().create(WebService::class.java)
    }
}