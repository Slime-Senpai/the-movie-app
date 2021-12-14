package fr.mbds.dtla.movieapp.ui.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.mbds.dtla.idbdata.data.Category
import fr.mbds.dtla.idbdata.data.Movie
import fr.mbds.dtla.movieapp.databinding.MovieListItemBinding

class MovieAdapter(private val items: List<Movie>, private val onClick: (movie: Movie) -> Unit) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: MovieListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val movieImage: ImageView = binding.movieImg

        fun bind(item: Movie) {
            binding.item = item
            binding.root.setOnClickListener {
                onClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(MovieListItemBinding.inflate(inflater, parent, false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])

        Picasso.get().load(items[position].poster).into(holder.movieImage)
    }
}