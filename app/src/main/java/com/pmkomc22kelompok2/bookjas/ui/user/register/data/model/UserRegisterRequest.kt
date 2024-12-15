package com.pmkomc22kelompok2.bookjas.ui.user.register.data.model

data class UserRegisterRequest (
    val nama: String,
    val email: String,
    val password: String,
    val password_confirmation: String
)