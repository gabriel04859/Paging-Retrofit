package com.gabrielribeiro.pading3recycler.domain.model

import com.google.gson.annotations.SerializedName

data class PixabayHitsResponse(val id: Int,
                               val pageURL: String,
                               @SerializedName("largeImageURL")
                               val imageURL: String)