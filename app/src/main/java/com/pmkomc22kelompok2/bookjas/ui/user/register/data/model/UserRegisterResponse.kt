package com.pmkomc22kelompok2.bookjas.ui.user.register.data.model

data class UserRegisterResponse(
    val data: UserData
)

data class UserData(
    val id: String,
    val nama: String,
    val email: String,
    val foto_profil: String?, // Nullable karena `null` pada respons
    val status: String
)