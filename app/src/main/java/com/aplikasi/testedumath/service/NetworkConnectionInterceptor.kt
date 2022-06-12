package com.aplikasi.testedumath.service

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import okhttp3.Interceptor
import okhttp3.Response
import java.net.SocketTimeoutException

class NetworkConnectionInterceptor(context: Context): Interceptor{

    private val appContext = context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isInternetAvailable())
            throw ConnectionException("Perangkat tidak terhubung ke internet")

        try{
            return chain.proceed(chain.request())
        } catch (e: SocketTimeoutException) {
            throw ConnectionException("Gagal terhubung ke server")
        } catch (e: Exception) {
            throw ConnectionException("Error: ${e.message}")
        }
    }

    fun isInternetAvailable(): Boolean {
        var result = false
        val connectivityManager = appContext.
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            val actNw =
                connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
            result = when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.run {
                @Suppress("DEPRECATION")
                connectivityManager.activeNetworkInfo?.run {
                    result = when (type) {
                        ConnectivityManager.TYPE_WIFI -> true
                        ConnectivityManager.TYPE_MOBILE -> true
                        ConnectivityManager.TYPE_ETHERNET -> true
                        else -> false
                    }

                }
            }
        }

        return result
    }

}