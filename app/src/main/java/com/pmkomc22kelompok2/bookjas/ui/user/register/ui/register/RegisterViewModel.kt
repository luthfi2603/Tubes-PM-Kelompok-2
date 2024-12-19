package com.pmkomc22kelompok2.bookjas.ui.user.register.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Patterns
import com.pmkomc22kelompok2.bookjas.R
import com.pmkomc22kelompok2.bookjas.ui.login.ui.login.LoginFormState

class RegisterViewModel() : ViewModel() {

    private val _registerForm = MutableLiveData<RegisterFormState>()
    val registerFormState: LiveData<RegisterFormState> = _registerForm

    fun registerDataChanged(nama: String, email: String, password: String, konfirmasiPassword: String) {
        if (nama.length > 255) {
            _registerForm.value = RegisterFormState(namaError = R.string.invalid_nama)
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) { // Kalau emailnya tidak dalam bentuk example@gmail.com
            _registerForm.value = RegisterFormState(emailError = R.string.invalid_email)
        } else if (email.length > 255) {
            _registerForm.value = RegisterFormState(emailError = R.string.invalid_email_max_length)
        } else if (password.length < 8) {
            _registerForm.value = RegisterFormState(passwordError = R.string.invalid_password_min_length)
        } else if (password.length > 255) {
            _registerForm.value = RegisterFormState(passwordError = R.string.invalid_password_max_length)
        } else if (konfirmasiPassword.length < 8) {
            _registerForm.value = RegisterFormState(konfirmasiPasswordError = R.string.invalid_konfirmasi_password_min_length)
        } else if (konfirmasiPassword.length > 255) {
            _registerForm.value = RegisterFormState(konfirmasiPasswordError = R.string.invalid_konfirmasi_password_max_length)
        } else if (password != konfirmasiPassword) {
            _registerForm.value = RegisterFormState(konfirmasiPasswordError = R.string.password_tidak_sama)
        } else {
            _registerForm.value = RegisterFormState(isDataValid = true)
        }
    }
}