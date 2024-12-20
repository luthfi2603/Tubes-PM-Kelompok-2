package com.pmkomc22kelompok2.bookjas.api

import com.pmkomc22kelompok2.bookjas.api.ApiClient.UserManager.BASE_URL
import com.pmkomc22kelompok2.bookjas.ui.login.data.model.UserLoginResponseData
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    object UserManager {
        const val BASE_URL = "http://192.168.150.210:8000" // ganti sesuai ip computer kita yang terhubung ke internet
    }

    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}