package mx.com.disoftware.movieconpendium.ui.movie

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import mx.com.disoftware.movieconpendium.R
import mx.com.disoftware.movieconpendium.core.Resource
import mx.com.disoftware.movieconpendium.data.model.Movie
import mx.com.disoftware.movieconpendium.data.remote.MovieDataSource
import mx.com.disoftware.movieconpendium.databinding.FragmentMovieBinding
import mx.com.disoftware.movieconpendium.presentation.MovieViewModel
import mx.com.disoftware.movieconpendium.presentation.MovieViewModelFactory
import mx.com.disoftware.movieconpendium.repository.MovieRepositoryImpl
import mx.com.disoftware.movieconpendium.repository.RetrofitClient
import mx.com.disoftware.movieconpendium.ui.movie.adapters.MovieAdapter
import mx.com.disoftware.movieconpendium.ui.movie.adapters.concat.PopularConcatAdapter
import mx.com.disoftware.movieconpendium.ui.movie.adapters.concat.TopRatedConcatAdapter
import mx.com.disoftware.movieconpendium.ui.movie.adapters.concat.UpcomingConcatAdapter

class MovieFragment : Fragment(R.layout.fragment_movie), MovieAdapter.OnMovieClickListener {

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

    private lateinit var concatAdapter: ConcatAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieBinding.bind(view)

        concatAdapter = ConcatAdapter()

        // Se hacen las llamadas de manera secuencial, primero la de coming, luego rated y al último popular.
        viewModel.fetchMainScreenMovies().observe(viewLifecycleOwner, Observer { result ->
            when(result) {
                is Resource.Loanding -> {
                    binding.progressBar.visibility = View.VISIBLE
                    Log.d("LiveData", "Loading...")
                }
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    concatAdapter.apply {
                        addAdapter(0, UpcomingConcatAdapter(MovieAdapter(result.data.first.results, this@MovieFragment)))
                        addAdapter(1, TopRatedConcatAdapter(MovieAdapter(result.data.second.results, this@MovieFragment)))
                        addAdapter(2, PopularConcatAdapter(MovieAdapter(result.data.third.results, this@MovieFragment)))
                    }
                    binding.rvMovies.adapter = concatAdapter
                    Log.d("LiveData", "¡Successful!")
                    Log.d("LiveData", "UpComing: ${result.data.first} \n ToRated: ${result.data.second} \n Popular: ${result.data.third}")
                }
                is Resource.Failure -> {
                    binding.progressBar.visibility = View.GONE
                    Log.d("Error", "${result.exception}")
                }
            }
        })

    }

    override fun onMovieClick(movie: Movie) {
        val action = MovieFragmentDirections.actionMovieFragmentToMovieDetailFragment(
            movie.poster_path,
            movie.backdrop_path,
            movie.vote_average.toFloat(),
            movie.vote_count,
            movie.overview,
            movie.title,
            movie.original_language,
            movie.release_date
        )
        findNavController().navigate(action)
        Log.d("Movie", "onMovieClick: $movie")
    }

}