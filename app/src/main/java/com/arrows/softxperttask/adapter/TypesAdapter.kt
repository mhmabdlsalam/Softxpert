package com.arrows.softxperttask.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.arrows.domain.model.TypeX
import com.arrows.softxperttask.databinding.AnimalTypeItemBinding

class TypesAdapter  : ListAdapter<String, TypesAdapter.ViewHolder>(DiffCallback()) {
    var onItemClick: ((String?) -> Unit)? = null
    var lastCheckPosition = 0 ;
    inner class ViewHolder(private val itemBinding: AnimalTypeItemBinding): RecyclerView.ViewHolder(itemBinding.root){
        fun bind(position: Int){
            itemBinding.apply {
                animalType.text= getItem(position)

                card.strokeColor = if (position==lastCheckPosition)Color.BLUE
                else Color.WHITE

                card.setOnClickListener {
                    val copyLastCheckPosition =lastCheckPosition
                    lastCheckPosition = position
                    notifyItemChanged(copyLastCheckPosition)
                    notifyItemChanged(lastCheckPosition)
                    if (position==0){
                        onItemClick?.invoke(null)
                    }else{
                        onItemClick?.invoke(getItem(position))
                    }

                }


            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            AnimalTypeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)

    }

    class DiffCallback : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }

}
