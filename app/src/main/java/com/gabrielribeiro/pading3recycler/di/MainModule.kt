package com.gabrielribeiro.pading3recycler.di

import com.gabrielribeiro.pading3recycler.domain.network.DataSource
import com.gabrielribeiro.pading3recycler.domain.network.PixaBayApi
import com.gabrielribeiro.pading3recycler.domain.repository.PixabayRepository
import com.gabrielribeiro.pading3recycler.domain.repository.PixabayRepositoryImpl
import com.gabrielribeiro.pading3recycler.presentation.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {

    single { PixaBayApi.providePixabayApi() }
    single<PixabayRepository> { PixabayRepositoryImpl(get()) }
    factory { DataSource(get(), get()) }
    factory { String }
    viewModel {
        HomeViewModel(
            repository = get()
        )
    }

}