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

        // Se hacen las llamadas de manera secuencial, primero la de coming, luego rated y al último popular.
        viewModel.fetchMainScreenMovies().observe(viewLifecycleOwner, Observer { result ->
            when(result) {
                is Resource.Loanding -> {
                    Log.d("LiveData", "Loading...")
                }
                is Resource.Success -> {
                    Log.d("LiveData", "¡Successful!")
                    Log.d("LiveData", "UpComing: ${result.data.first} \n ToRated: ${result.data.second} \n Popular: ${result.data.third}")
                }
                is Resource.Failure -> {
                    Log.d("Error", "${result.exception}")
                }
            }
        })

    }

}