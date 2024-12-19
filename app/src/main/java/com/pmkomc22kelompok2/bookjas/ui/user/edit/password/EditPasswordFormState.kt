package com.pmkomc22kelompok2.bookjas.ui.user.edit.password

data class EditPasswordFormState(
    val passwordSaatIniError: Int? = null,
    val passwordBaruError: Int? = null,
    val konfirmasiPasswordBaruError: Int? = null,
    val isDataValid: Boolean = false
)
