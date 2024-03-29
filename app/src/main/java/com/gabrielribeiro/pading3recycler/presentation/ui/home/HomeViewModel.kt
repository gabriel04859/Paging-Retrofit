package com.gabrielribeiro.pading3recycler.presentation.ui.home

import androidx.lifecycle.*
import androidx.paging.*
import com.gabrielribeiro.pading3recycler.domain.repository.PixabayRepository

class HomeViewModel(repository: PixabayRepository): ViewModel() {

    val imageList = repository.providePixabayApi().cachedIn(viewModelScope)

    class MainViewModelFactory(private val repository: PixabayRepository): ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return HomeViewModel(repository) as T
        }
    }
}