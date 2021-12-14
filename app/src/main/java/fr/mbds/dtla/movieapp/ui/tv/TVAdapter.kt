package fr.mbds.dtla.movieapp.ui.tv

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.mbds.dtla.idbdata.data.TVShow
import fr.mbds.dtla.movieapp.databinding.TvshowsListItemBinding

class TVAdapter(private val items: List<TVShow>, private val onClick: (tv: TVShow) -> Unit) :
    RecyclerView.Adapter<TVAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: TvshowsListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val tvImage: ImageView = binding.tvshowImg

        fun bind(item: TVShow) {
            binding.item = item
            binding.root.setOnClickListener {
                onClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(TvshowsListItemBinding.inflate(inflater, parent, false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])

        Picasso.get().load(items[position].poster).into(holder.tvImage)
    }
}