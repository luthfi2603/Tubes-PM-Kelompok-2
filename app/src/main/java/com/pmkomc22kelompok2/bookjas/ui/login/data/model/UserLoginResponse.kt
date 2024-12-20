package com.pmkomc22kelompok2.bookjas.ui.login.data.model

data class UserLoginResponse(
    val data: UserLoginResponseData
)

data class UserLoginResponseData(
    var id: String?,
    var email: String?,
    var nama: String?,
    var token: String?,
    var status: String?,
    var password: String?,
)