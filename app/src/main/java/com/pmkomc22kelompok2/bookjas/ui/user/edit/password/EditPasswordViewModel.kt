package com.pmkomc22kelompok2.bookjas.ui.user.edit.password

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pmkomc22kelompok2.bookjas.R

class EditPasswordViewModel  : ViewModel() {
    private val _editPasswordForm = MutableLiveData<EditPasswordFormState>()
    val editPasswordFormState: LiveData<EditPasswordFormState> = _editPasswordForm

    fun registerDataChanged(passwordSaatIni: String, passwordBaru: String, konfirmasiPasswordBaru: String) {
        if (passwordSaatIni.length < 8) {
            _editPasswordForm.value = EditPasswordFormState(passwordSaatIniError = R.string.invalid_password_min_length)
        } else if (passwordSaatIni.length > 255) {
            _editPasswordForm.value = EditPasswordFormState(passwordSaatIniError = R.string.invalid_password_max_length)
        } else if (passwordBaru.length < 8) {
            _editPasswordForm.value = EditPasswordFormState(passwordBaruError = R.string.invalid_password_min_length)
        } else if (passwordBaru.length > 255) {
            _editPasswordForm.value = EditPasswordFormState(passwordBaruError = R.string.invalid_password_max_length)
        } else if (konfirmasiPasswordBaru.length < 8) {
            _editPasswordForm.value = EditPasswordFormState(konfirmasiPasswordBaruError = R.string.invalid_password_min_length)
        } else if (konfirmasiPasswordBaru.length > 255) {
            _editPasswordForm.value = EditPasswordFormState(konfirmasiPasswordBaruError = R.string.invalid_password_max_length)
        } else if (passwordBaru != konfirmasiPasswordBaru) {
            _editPasswordForm.value = EditPasswordFormState(konfirmasiPasswordBaruError = R.string.password_tidak_sama)
        } else {
            _editPasswordForm.value = EditPasswordFormState(isDataValid = true)
        }
    }
}