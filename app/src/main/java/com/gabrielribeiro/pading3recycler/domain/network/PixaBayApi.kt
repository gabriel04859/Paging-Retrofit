package com.gabrielribeiro.pading3recycler.domain.network

import com.gabrielribeiro.pading3recycler.domain.model.PixabayResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface PixaBayApi {

    @GET("/api")
    suspend fun getPhotos(@Query("page") page: Int, @Query("q") query: String?, @Query("key") key: String = KEY): Response<PixabayResponse>

    companion object{
        private const val BASE_URL = "https://pixabay.com/"
        private const val KEY = "23828128-7693588594dfc604de9b8678c"

        fun providePixabayApi(): PixaBayApi {
            val logging = HttpLoggingInterceptor().apply {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            }
            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()

            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .baseUrl(BASE_URL)
                .build()
                .create(PixaBayApi::class.java)
        }
    }
}