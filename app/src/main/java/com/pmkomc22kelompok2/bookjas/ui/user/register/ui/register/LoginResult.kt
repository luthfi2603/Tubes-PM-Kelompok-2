package com.pmkomc22kelompok2.bookjas.ui.user.register.ui.register

/**
 * Authentication result : success (user details) or error message.
 */
data class LoginResult(
    val success: LoggedInUserView? = null,
    val error: Int? = null
)