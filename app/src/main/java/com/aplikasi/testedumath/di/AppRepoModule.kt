package com.aplikasi.testedumath.di

import com.aplikasi.testedumath.service.AppInterface
import com.aplikasi.testedumath.service.AppRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val appRepoModule = module {
    factory { AppRepository(get<Retrofit>(named(appService)).create(AppInterface::class.java)) }
}