package com.pmkomc22kelompok2.bookjas.api

import com.pmkomc22kelompok2.bookjas.data.OkResponse
import com.pmkomc22kelompok2.bookjas.ui.admin.buku.BukuAdminResponse
import com.pmkomc22kelompok2.bookjas.ui.login.data.model.UserLoginRequest
import com.pmkomc22kelompok2.bookjas.ui.login.data.model.UserLoginResponse
import com.pmkomc22kelompok2.bookjas.ui.user.dashboard.ListKategoriResponse
import com.pmkomc22kelompok2.bookjas.ui.user.edit.password.EditPasswordRequest
import com.pmkomc22kelompok2.bookjas.ui.user.register.data.model.UserRegisterRequest
import com.pmkomc22kelompok2.bookjas.ui.user.register.data.model.UserRegisterResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface ApiService {
    @POST("/api/login")
    fun login(@Body userLoginRequest: UserLoginRequest): Call<UserLoginResponse>

    @POST("/api/register")
    fun registerUser(@Body userLoginRequest: UserRegisterRequest): Call<UserRegisterResponse>

    @GET("/api/kategori")
    fun getKategori(): Call<ListKategoriResponse>

    @POST("/api/logout")
    fun logout(@Header ("Authorization") token: String?): Call<Void>

    @PATCH("/api/update-password")
    fun editPassword(@Body editPasswordRequest: EditPasswordRequest, @Header ("Authorization") token: String?): Call<OkResponse>

    @GET("/api/books")
    fun getBooks(@Header ("Authorization") token: String?): Call<BukuAdminResponse>
}