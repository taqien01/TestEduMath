package com.aplikasi.testedumath.di

import com.aplikasi.testedumath.viewmodel.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    viewModel { MainViewModel(get ()) }
}