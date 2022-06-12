package com.aplikasi.testedumath.service

import com.aplikasi.testedumath.model.Data
import retrofit2.Response
import retrofit2.http.GET

interface AppInterface {

    //get data
    @GET("eureka/questions")
    suspend fun getData(): Response<List<Data>>

}