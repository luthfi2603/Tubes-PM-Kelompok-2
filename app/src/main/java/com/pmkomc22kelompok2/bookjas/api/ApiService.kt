package com.pmkomc22kelompok2.bookjas.api

import com.pmkomc22kelompok2.bookjas.ui.login.data.model.UserLoginRequest
import com.pmkomc22kelompok2.bookjas.ui.login.data.model.UserLoginResponse
import com.pmkomc22kelompok2.bookjas.ui.user.dashboard.ListKategoriResponse
import com.pmkomc22kelompok2.bookjas.ui.user.register.data.model.UserRegisterRequest
import com.pmkomc22kelompok2.bookjas.ui.user.register.data.model.UserRegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {
    @POST("/api/login")
    fun login(@Body userLoginRequest: UserLoginRequest): Call<UserLoginResponse>

    @POST("/api/register")
    fun registerUser(@Body userLoginRequest: UserRegisterRequest): Call<UserRegisterResponse>

    @GET("/api/kategori")
    fun getKategori(): Call<ListKategoriResponse>

    @POST("/api/logout")
    fun logout(@Header ("Authorization") token: String?): Call<Void>
}