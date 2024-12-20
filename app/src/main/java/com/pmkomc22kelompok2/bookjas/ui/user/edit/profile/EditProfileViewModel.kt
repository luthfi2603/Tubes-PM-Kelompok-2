package com.pmkomc22kelompok2.bookjas.ui.user.edit.profile

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pmkomc22kelompok2.bookjas.R

class EditProfileViewModel  : ViewModel() {
    private val _editProfileForm = MutableLiveData<EditProfileFormState>()
    val editProfileFormState: LiveData<EditProfileFormState> = _editProfileForm

    fun editProfileDataChanged(nama: String, email: String) {
        if (nama.length > 255) {
            _editProfileForm.value = EditProfileFormState(namaError = R.string.invalid_nama)
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) { // Kalau emailnya tidak dalam bentuk example@gmail.com
            _editProfileForm.value = EditProfileFormState(emailError = R.string.invalid_email)
        } else if (email.length > 255) {
            _editProfileForm.value = EditProfileFormState(emailError = R.string.invalid_email_max_length)
        } else {
            _editProfileForm.value = EditProfileFormState(isDataValid = true)
        }
    }
}