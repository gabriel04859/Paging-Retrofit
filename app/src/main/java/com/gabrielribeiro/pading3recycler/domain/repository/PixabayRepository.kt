package com.gabrielribeiro.pading3recycler.domain.repository

import androidx.paging.PagingData
import com.gabrielribeiro.pading3recycler.domain.model.PixabayHitsResponse
import com.gabrielribeiro.pading3recycler.domain.model.PixabayResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface PixabayRepository {
    fun providePixabayApi(): Flow<PagingData<PixabayHitsResponse>>
}