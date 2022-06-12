package com.aplikasi.testedumath.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aplikasi.testedumath.model.Data
import com.aplikasi.testedumath.service.AppRepository
import com.aplikasi.testedumath.utils.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import java.net.HttpURLConnection

class MainViewModel(private val appRepository: AppRepository ): ViewModel() {

    val errorEvent = SingleLiveEvent<String>()
    val loadingEvent = SingleLiveEvent<Boolean>()
    val listEvent = SingleLiveEvent<List<Data>>()

    fun getData(){
        loadingEvent.value = true

        var response :Response<List<Data>>

        viewModelScope.launch {
            with(Dispatchers.IO){
                response = appRepository.getData()
            }

            with(Dispatchers.Main){
                try {
                    if (response.code() == HttpURLConnection.HTTP_OK){
                            listEvent.value = response.body()
                    }else{
                        errorEvent.value = "Terjadi Kesalahan : "+response.code()
                    }
                }catch (e : Exception){
                    errorEvent.value = e.localizedMessage
                }finally {
                    loadingEvent.value = false
                }
            }
        }
    }
}