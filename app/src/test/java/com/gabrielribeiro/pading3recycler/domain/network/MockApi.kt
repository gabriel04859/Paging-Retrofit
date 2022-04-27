package com.gabrielribeiro.pading3recycler.domain.network

import com.gabrielribeiro.pading3recycler.domain.model.PixabayHitsResponse
import com.gabrielribeiro.pading3recycler.domain.model.PixabayResponse
import retrofit2.Response

class MockApi : PixaBayApi {
    private var photosList = mutableListOf<PixabayResponse>()
    fun addPhotos(name: String) {
        photosList.add(PixabayResponse(1, 1, listOf(PixabayHitsResponse(1, "url", "url"))))
    }

    override suspend fun getPhotos(
        page: Int,
        query: String?,
        key: String
    ): Response<PixabayResponse> {
        return  Response.success(PixabayResponse(1, 1, listOf(PixabayHitsResponse(1, "mockURL", "mockImage"))))
    }
}