package fr.mbds.dtla.movieapp.ui.hometv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.mbds.dtla.idbdata.data.Category
import fr.mbds.dtla.movieapp.databinding.CategoryTvListItemBinding

class CategoryTVAdapter(private val items: List<Category>, private val onClick: (category: Category) -> Unit) :
    RecyclerView.Adapter<CategoryTVAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: CategoryTvListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Category) {
            binding.item = item
            binding.root.setOnClickListener {
                onClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(CategoryTvListItemBinding.inflate(inflater, parent, false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }
}