package com.gabrielribeiro.pading3recycler.presentation.ui.home

import androidx.lifecycle.*
import androidx.paging.*
import com.gabrielribeiro.pading3recycler.domain.repository.PixabayRepository

class HomeViewModel(repository: PixabayRepository): ViewModel() {

    private val currentQuery = MutableLiveData<String?>(null)

    fun searchQuery(query: String) {
        currentQuery.value = query
    }

    val imageList = currentQuery.switchMap {
        repository.getPixaybayList(it).cachedIn(viewModelScope)
    }


}