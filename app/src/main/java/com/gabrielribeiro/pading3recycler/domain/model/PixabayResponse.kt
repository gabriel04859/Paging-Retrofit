package com.gabrielribeiro.pading3recycler.domain.model

import com.google.gson.annotations.SerializedName

data class PixabayResponse(
    val total: Int,
    val totalHits: Int,
    @SerializedName("hits")
    val pixabayImage: List<PixabayHitsResponse>
)
