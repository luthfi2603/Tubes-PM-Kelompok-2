package com.pmkomc22kelompok2.bookjas.ui.user.edit.password

data class EditPasswordRequest(
    val current_password: String,
    val password: String,
    val password_confirmation: String
)
