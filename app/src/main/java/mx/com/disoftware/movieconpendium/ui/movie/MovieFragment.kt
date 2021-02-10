package mx.com.disoftware.movieconpendium.ui.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import mx.com.disoftware.movieconpendium.R
import mx.com.disoftware.movieconpendium.databinding.FragmentMovieBinding

class MovieFragment : Fragment(R.layout.fragment_movie) {

    /**
     *  Obs. onViewCreated se visualiza que se pasa en el constructor de este fragment el recurso R.layout.fragment_movie.
     *  y en el método onViewCreated se puede acceder a la vista (R.layout.fragment_movie) con el parámetro view, de esta
     *  manera ya no es necesario onCreateView.
     */

    private lateinit var binding: FragmentMovieBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentMovieBinding.bind(view)
        /* acceder a los recuros definidos en la vista R.layout.fragment_movie.
           NOTA, los nomnbres de los recursos los realiza con notación camel case, no como originalmnete se colocaron en el xml.
           del fragmento (R.layout.fragment_movie).
         */
        binding.progressBar.visibility = View.GONE

    }

}