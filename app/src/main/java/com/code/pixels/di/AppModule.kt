package com.code.pixels.di

import com.code.pixels.data.FlicksRepository
import com.code.pixels.data.FlicksRepositoryImpl
import com.code.pixels.ui.fragments.DetailViewModel
import com.code.pixels.ui.fragments.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {
    // single instance of HelloRepository
    single<FlicksRepository> { FlicksRepositoryImpl() }

    viewModel { MainViewModel() }

    viewModel { DetailViewModel() }

}

fun getModule() = appModule