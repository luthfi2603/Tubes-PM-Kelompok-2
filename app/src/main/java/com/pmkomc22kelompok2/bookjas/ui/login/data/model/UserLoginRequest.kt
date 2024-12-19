package com.pmkomc22kelompok2.bookjas.ui.login.data.model

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
data class UserLoginRequest(
    val email: String,
    val password: String
)