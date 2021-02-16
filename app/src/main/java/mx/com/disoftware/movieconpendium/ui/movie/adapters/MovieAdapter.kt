package mx.com.disoftware.movieconpendium.ui.movie.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import mx.com.disoftware.movieconpendium.core.BaseViewHolder
import mx.com.disoftware.movieconpendium.data.model.Movie
import mx.com.disoftware.movieconpendium.databinding.MovieItemBinding

// *: indica que será cualquier tipo de View holder.
class MovieAdapter(
        private val moviesList: List<Movie>,
        private val itemClickListener: OnMovieClickListener
) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface OnMovieClickListener {
        fun onMovieClick(movie: Movie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        // Tomamos el binding del xml movie_item generado.
        // Se va a buscar como se va a ver cada uno de los elementos a mostrar en la lista de películas (pantalla principal).
        val itemBinding = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = MoviesViewHolder(itemBinding, parent.context)

        // agregar un evento OnClick a cada película, para ir a sus detalles.
        itemBinding.root.setOnClickListener {
            // obtener la posicion del viewholder que estoy cliqueando.
            val position =
                    holder.absoluteAdapterPosition.takeIf {
                        it != DiffUtil.DiffResult.NO_POSITION
                    }
                            ?: return@setOnClickListener // toma la posicion en la cual se hace click y devolver esa posición. Si no en cuentra nada, no se devuelve nada.
            itemClickListener.onMovieClick(moviesList[position])
        }
        return holder
    }

    // Setear cada elemento de la lista cada película obteneida.
    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder) {
            is MoviesViewHolder -> holder.bind(moviesList[position])
        }
    }

    override fun getItemCount() = moviesList.size

    // inner significa que está sujeto al ciclo de vida de la clase padre, cuando ésta deja de existir(o cuando se crea , también susu hijas), también sus clases hija de tipo inner.
    // Con root se puede acceder a todos los elementos de la layauot.
    private inner class MoviesViewHolder(
            val binding: MovieItemBinding,
            val context: Context) : BaseViewHolder<Movie>(binding.root) {
        override fun bind(item: Movie) {
            // centerCrop: sirve para poder adaptar la imagen al contenedor.
            Glide.with(context).load("https://image.tmdb.org/t/p/w500/${item.poster_path}").centerCrop().into(binding.imgMovie)
        }

    }

}