package com.gabrielribeiro.pading3recycler.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.gabrielribeiro.pading3recycler.domain.model.PixabayHitsResponse
import com.gabrielribeiro.pading3recycler.domain.network.DataSource
import com.gabrielribeiro.pading3recycler.domain.network.PixaBayApi
import kotlinx.coroutines.flow.Flow

class PixabayRepositoryImpl(private val pixabayApi: PixaBayApi): PixabayRepository {

    override fun providePixabayApi(): Flow<PagingData<PixabayHitsResponse>> {
        return Pager(PagingConfig(pageSize = 20,maxSize = 100,enablePlaceholders = false)){
            DataSource(pixabayApi)
        }.flow
    }
}