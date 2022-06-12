package com.aplikasi.testedumath

import android.content.Context
import androidx.multidex.MultiDexApplication
import com.aplikasi.testedumath.di.appRepoModule
import com.aplikasi.testedumath.di.mainModule
import com.aplikasi.testedumath.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()

        context = applicationContext

        startKoin {

            androidContext(this@MainApplication)

            modules(
                appRepoModule,
                networkModule,
                mainModule
            )
        }
    }

    companion object {
        private lateinit var context: Context

        fun getContext(): Context {
            return context
        }
    }
}