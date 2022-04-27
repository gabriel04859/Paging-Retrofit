package com.gabrielribeiro.pading3recycler.domain.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.gabrielribeiro.pading3recycler.domain.model.PixabayHitsResponse
import com.gabrielribeiro.pading3recycler.domain.network.DataSource
import com.gabrielribeiro.pading3recycler.domain.network.PixaBayApi
import kotlinx.coroutines.flow.Flow

class PixabayRepositoryImpl(private val pixabayApi: PixaBayApi): PixabayRepository {

    override fun getPixaybayList(query: String?): LiveData<PagingData<PixabayHitsResponse>> {
        return Pager(PagingConfig(pageSize = 20,maxSize = 100,enablePlaceholders = false)){
            DataSource(pixabayApi, query)
        }.liveData
    }
}