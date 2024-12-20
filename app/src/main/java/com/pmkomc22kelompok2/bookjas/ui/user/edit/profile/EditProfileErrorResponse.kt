package com.pmkomc22kelompok2.bookjas.ui.user.edit.profile

data class EditProfileErrorResponse(
    val errors: EditProfileErrorResponseData
)

data class EditProfileErrorResponseData(
    val nama: List<String>?,
    val email: List<String>?,
)