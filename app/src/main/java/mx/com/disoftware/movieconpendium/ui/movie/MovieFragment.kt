package mx.com.disoftware.movieconpendium.ui.movie

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import mx.com.disoftware.movieconpendium.R
import mx.com.disoftware.movieconpendium.core.Resource
import mx.com.disoftware.movieconpendium.data.remote.MovieDataSource
import mx.com.disoftware.movieconpendium.databinding.FragmentMovieBinding
import mx.com.disoftware.movieconpendium.presentation.MovieViewModel
import mx.com.disoftware.movieconpendium.presentation.MovieViewModelFactory
import mx.com.disoftware.movieconpendium.repository.MovieRepositoryImpl
import mx.com.disoftware.movieconpendium.repository.RetrofitClient

class MovieFragment : Fragment(R.layout.fragment_movie) {

    /**
     *  Obs. onViewCreated se visualiza que se pasa en el constructor de este fragment el recurso R.layout.fragment_movie.
     *  y en el método onViewCreated se puede acceder a la vista (R.layout.fragment_movie) con el parámetro view, de esta
     *  manera ya no es necesario onCreateView.
     */
    private lateinit var binding: FragmentMovieBinding

    /**
     * se crea el view model meidante un delegador (by), el cual delega la creación del objeto mediante inyacción de
     * dependencia manual, es decir; que se crea un viewModel a su vez el solicita un ModelFactory, que éste requiere
     * de la implementación del repositorio que requiere de MovieDataSource (petlicón de la data al servidor remoto),
     * mediante el consumo del servicio web.
     */
    private val viewModel by viewModels<MovieViewModel> {
        MovieViewModelFactory(
            MovieRepositoryImpl(MovieDataSource(
                RetrofitClient.webService
            ))
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentMovieBinding.bind(view)
        /* acceder a los recuros definidos en la vista R.layout.fragment_movie.
           NOTA, los nomnbres de los recursos los realiza con notación camel case, no como originalmnete se colocaron en el xml.
           del fragmento (R.layout.fragment_movie).
         */
        binding.progressBar.visibility = View.GONE

        /**
         * Cada vez que se ejecute un emit (MovieViewModel), se notificará al observable.
         * En otras palabras nos estamos subscriviendo a fetchUpComingMovies, que devuelve un LiveData, con
         * las posibilidades que fetchUpComingMovies regrese (trayendo la info [Loanding], info obtenida sin
         * presentar problema [Success] y por ultimo notificar en caso que ocurra algún error [failure]).
         * viewLifecycleOwner, se encarga de gestionar el boserver, es decir, que no necesitamos subcribir
         * o desubscribir, ya que esta clase lo hace por nosostros.
         */
        viewModel.fetchUpComingMovies().observe(viewLifecycleOwner, Observer { result ->
            when(result) {
                is Resource.Loanding -> {
                    Log.d("LiveData_ComingMovies", "Loanding...")
                }
                is Resource.Success -> {
                    Log.d("LiveData_ComingMovies", "¡information's Successful!")
                    Log.d("LiveData_ComingMovies", "${result.data}")
                }
                is Resource.Failure -> {
                    Log.d("Error_ComingMovies", "${result.exception}")
                }
            }
        })

        viewModel.fetchUpPopularMovies().observe(viewLifecycleOwner, Observer { result ->
            when(result) {
                is Resource.Loanding -> {
                    Log.d("LiveData_PopularMovies", "Loanding...")
                }
                is Resource.Success -> {
                    Log.d("LiveData_PopularMovies", "¡information's Successful!")
                    Log.d("LiveData_PopularMovies", "${result.data}")
                }
                is Resource.Failure -> {
                    Log.d("Error", "${result.exception}")
                }
            }
        })

        viewModel.fetchUpRatedMovies().observe(viewLifecycleOwner, Observer { result ->
            when(result) {
                is Resource.Loanding -> {
                    Log.d("LiveData_RatedMovies", "Loanding...")
                }
                is Resource.Success -> {
                    Log.d("LiveData_RatedMovies", "¡information's Successful!")
                    Log.d("LiveData_RatedMovies", "${result.data}")
                }
                is Resource.Failure -> {
                    Log.d("Error_RatedMovies", "${result.exception}")
                }
            }
        })

        // Nota: puede acabar cualquier peticion antes que otra.
    }

}