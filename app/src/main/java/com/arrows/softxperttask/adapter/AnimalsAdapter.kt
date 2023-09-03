package com.arrows.softxperttask.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.arrows.domain.model.Animal
import com.arrows.softxperttask.R
import com.arrows.softxperttask.databinding.AnimalItemBinding
import com.squareup.picasso.Picasso

class AnimalsAdapter : PagingDataAdapter<Animal, AnimalsAdapter.ViewHolder>(DiffCallback()) {

    var onItemClick: ((Int) -> Unit)? = null

    inner class ViewHolder(private val itemBinding: AnimalItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(position: Int) {
            getItem(position)?.let { animal ->
                if (animal.name.isNullOrEmpty()) {
                    itemBinding.animalName.text = "NA"
                } else {
                    itemBinding.animalName.text = animal.name
                }
                if (animal.gender.isNullOrEmpty()) {
                    itemBinding.animalGender.text = "NA"
                } else {
                    itemBinding.animalGender.text = animal.gender
                }

                if (animal.type.isNullOrEmpty()) {
                    itemBinding.animalType.text = "NA"
                } else {
                    itemBinding.animalType.text = animal.type
                }

                if (animal.photos.isNullOrEmpty()||animal.photos[0].small.isNullOrEmpty()) {
                    Picasso.get().load(R.drawable.placeholder)
                        .into(itemBinding.imageView)
                } else {
                    Picasso.get().load(animal.photos[0].small).fit()
                        .error(R.drawable.placeholder).fit()
                        .into(itemBinding.imageView)
                }

                itemBinding.root.setOnClickListener {
                    onItemClick?.invoke(animal.id)
                }
            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            AnimalItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)

    }

    class DiffCallback : DiffUtil.ItemCallback<Animal>() {
        override fun areItemsTheSame(oldItem: Animal, newItem: Animal): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Animal, newItem: Animal): Boolean {
            return oldItem == newItem
        }
    }

}
