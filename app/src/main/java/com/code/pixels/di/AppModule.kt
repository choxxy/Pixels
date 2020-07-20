package com.code.pixels.di

import com.code.pixels.data.api.FlickrApiService
import com.code.pixels.data.repository.FlicksRepository
import com.code.pixels.data.repository.FlicksRepositoryImpl
import com.code.pixels.ui.viewmodels.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {

    // single instance of FlickrApiService
    single { FlickrApiService() }

    // single instance of FlicksRepository
    single<FlicksRepository> { FlicksRepositoryImpl(get()) }

    viewModel { MainViewModel(get()) }

}

fun getModule() = appModule