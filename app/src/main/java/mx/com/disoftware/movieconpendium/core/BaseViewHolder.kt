package mx.com.disoftware.movieconpendium.core

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Se reuzará para crear los adaptadores.
 */
abstract class BaseViewHolder<T>(itemView: View): RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: T)
}