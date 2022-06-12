package com.aplikasi.testedumath.di

import com.aplikasi.testedumath.service.NetworkConnectionInterceptor
import com.aplikasi.testedumath.service.AppService
import org.koin.core.qualifier.named
import org.koin.dsl.module

val networkModule = module {
    single { NetworkConnectionInterceptor(get()) }

    single(named(appService)) { AppService.getServices(get())  }
}

const val appService = "APP_SERVICE"