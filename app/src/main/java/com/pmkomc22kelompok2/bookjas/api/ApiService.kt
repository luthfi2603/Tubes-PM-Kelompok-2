package com.pmkomc22kelompok2.bookjas.api

import com.pmkomc22kelompok2.bookjas.ui.login.data.model.UserLoginRequest
import com.pmkomc22kelompok2.bookjas.ui.login.data.model.UserResponseLogin
import com.pmkomc22kelompok2.bookjas.ui.user.register.data.model.UserRegisterRequest
import com.pmkomc22kelompok2.bookjas.ui.user.register.data.model.UserResponseRegister
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
        @POST("/api/login")
        fun login(@Body userLoginRequest: UserLoginRequest): Call<UserResponseLogin>

        @POST("/api/register")
        fun registerUser(@Body userLoginRequest: UserRegisterRequest): Call<UserResponseRegister>
}
