package com.aplikasi.testedumath.service

import com.aplikasi.testedumath.model.Data
import retrofit2.Response

class AppRepository(private val appInterface: AppInterface) {

    suspend fun getData() : Response<List<Data>> =
        appInterface.getData()

}