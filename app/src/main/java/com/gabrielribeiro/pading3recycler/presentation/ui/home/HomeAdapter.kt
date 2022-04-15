package com.gabrielribeiro.pading3recycler.presentation.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gabrielribeiro.pading3recycler.R
import com.gabrielribeiro.pading3recycler.domain.model.PixabayHitsResponse

class HomeAdapter : PagingDataAdapter<PixabayHitsResponse, HomeAdapter.ViewHolder>(
    DataDifferentiator
) {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imagePlaceholder: ImageView = view.findViewById(R.id.image_place_holder)
        fun bind(image: PixabayHitsResponse) {
            Glide.with(itemView.context)
                .load(image.imageURL)
                .into(imagePlaceholder)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.list_item, parent, false)
        )
    }

    object DataDifferentiator : DiffUtil.ItemCallback<PixabayHitsResponse>() {
        override fun areItemsTheSame(
            oldItem: PixabayHitsResponse,
            newItem: PixabayHitsResponse
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: PixabayHitsResponse,
            newItem: PixabayHitsResponse
        ): Boolean {
            return oldItem == newItem
        }

    }

}