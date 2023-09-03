package com.arrows.softxperttask.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.arrows.softxperttask.databinding.ItemLoadBinding

class FooterLoadStateAdapter : LoadStateAdapter<FooterLoadStateAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ViewHolder {
        return ViewHolder(
            ItemLoadBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    inner class ViewHolder(val binding: ItemLoadBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(loadState: LoadState) {
            binding.apply {
                when (loadState) {
                    is LoadState.Error -> {
                        loading.hide()
                        tvError.visibility = View.VISIBLE
                        tvError.text = loadState.error.localizedMessage
                    }
                    is LoadState.NotLoading -> {
                        loading.hide()
                        tvError.visibility = View.GONE
                    }

                    is LoadState.Loading -> {
                        loading.show()
                        tvError.visibility = View.GONE
                    }

                    else -> {}
                }

            }
        }
    }
}