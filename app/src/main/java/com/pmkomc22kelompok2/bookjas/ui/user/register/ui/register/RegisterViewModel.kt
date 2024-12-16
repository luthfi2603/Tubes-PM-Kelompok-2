package com.pmkomc22kelompok2.bookjas.ui.user.register.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Patterns
import com.pmkomc22kelompok2.bookjas.R
import com.pmkomc22kelompok2.bookjas.ui.user.register.data.Result

class RegisterViewModel() : ViewModel() {

    private val _registerForm = MutableLiveData<RegisterFormState>()
    val registerFormState: LiveData<RegisterFormState> = _registerForm

    fun registerDataChanged(nama: String, email: String, password: String, konfirmasiPassword: String) {
        if (!isNamaValid(nama)) {
            _registerForm.value = RegisterFormState(namaError = R.string.invalid_nama)
        } else if (!isEmailValid(email)) {
            _registerForm.value = RegisterFormState(emailError = R.string.invalid_email)
        } else if (!isPasswordValid(password)) {
            _registerForm.value = RegisterFormState(passwordError = R.string.invalid_password)
        } else if (!isKonfirmasiPasswordValid(konfirmasiPassword)) {
            _registerForm.value = RegisterFormState(konfirmasiPasswordError = R.string.invalid_konfirmasi_password)
        } else {
            _registerForm.value = RegisterFormState(isDataValid = true)
        }
    }

    // validasi nama
    private fun isNamaValid(nama: String): Boolean {
        return nama.length <= 255
    }

    // validasi email
    private fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    // validasi password
    private fun isPasswordValid(password: String): Boolean {
        return password.length in 8..255
    }

    // validasi konfirmasi password
    private fun isKonfirmasiPasswordValid(password: String): Boolean {
        return password.length in 8..255
    }
}