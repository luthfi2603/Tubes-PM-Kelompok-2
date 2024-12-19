package com.pmkomc22kelompok2.bookjas.ui.login.data.model

data class UserLoginResponse(
    val data: UserLoginResponseData
)

data class UserLoginResponseData(
    val id: String?,
    val email: String?,
    val nama: String?,
    val token: String?,
    val status: String?,
    val password: String?,
)