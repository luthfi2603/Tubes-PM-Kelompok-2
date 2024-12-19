package com.pmkomc22kelompok2.bookjas.ui.login.ui.login

import com.pmkomc22kelompok2.bookjas.ui.login.data.model.UserLoginResponseData

/**
 * Authentication result : success (user details) or error message.
 */
data class LoginResult(
    val success: UserLoginResponseData? = null,
    val error: Int? = null
)