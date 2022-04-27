package com.gabrielribeiro.pading3recycler.presentation.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gabrielribeiro.pading3recycler.databinding.LoadImageHolderBinding

class HomeLoadStateAdapter(private val onClickRetry: () -> Unit): LoadStateAdapter<HomeLoadStateAdapter.HomeLoadStateAdapterViewHolder>() {

    inner class HomeLoadStateAdapterViewHolder(private val binding: LoadImageHolderBinding): RecyclerView.ViewHolder(binding.root) {
        init {
            binding.buttonTryAgain.setOnClickListener {
                onClickRetry.invoke()
            }
        }
        fun bind(loadState: LoadState) {
            when(loadState) {
                is LoadState.NotLoading -> {

                }
                LoadState.Loading -> {

                }
                is LoadState.Error -> {

                }
            }
            binding.apply {
                progressBarLoadingList.isVisible = loadState is LoadState.Loading
                buttonTryAgain.isVisible = loadState !is LoadState.Loading
                textLoadError.isVisible = loadState !is LoadState.Loading
            }
        }
    }

    override fun onBindViewHolder(holder: HomeLoadStateAdapterViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): HomeLoadStateAdapterViewHolder {
        return HomeLoadStateAdapterViewHolder(LoadImageHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }
}