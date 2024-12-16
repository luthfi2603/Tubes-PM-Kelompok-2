package com.pmkomc22kelompok2.bookjas.ui.user.register.ui.register

/**
 * Data validation state of the login form.
 */
data class RegisterFormState(
    val namaError: Int? = null,
    val emailError: Int? = null,
    val passwordError: Int? = null,
    val konfirmasiPasswordError: Int? = null,
    val isDataValid: Boolean = false
)