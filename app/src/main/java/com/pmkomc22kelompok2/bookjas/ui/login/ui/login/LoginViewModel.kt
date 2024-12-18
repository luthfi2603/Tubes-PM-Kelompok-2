package com.pmkomc22kelompok2.bookjas.ui.login.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Patterns
import com.pmkomc22kelompok2.bookjas.R
import com.pmkomc22kelompok2.bookjas.ui.login.data.LoginRepository
import com.pmkomc22kelompok2.bookjas.ui.login.data.Result
import com.pmkomc22kelompok2.bookjas.ui.login.data.model.UserLoginResponseData

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    /*fun login(email: String, password: String) {
        // can be launched in a separate asynchronous job
        val result = loginRepository.login(email, password)

        if (result is Result.Success) {
            _loginResult.value =
                LoginResult(success = LoggedInUserView(result.data.nama))
        } else {
            _loginResult.value = LoginResult(error = R.string.login_failed)
        }
    }*/

    fun login(email: String, password: String) {
        // Panggil login di repository dan terima callback
        loginRepository.login(email, password) { result ->
            // Kirimkan hasil ke callback setelah login berhasil atau gagal
            if (result is Result.Success) {
                _loginResult.value =
                    LoginResult(success = LoggedInUserView(result.data.nama))
            } else {
                _loginResult.value = LoginResult(error = R.string.login_failed)
            }
        }
    }

    fun loginDataChanged(email: String, password: String) {
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) { // Kalau emailnya tidak dalam bentuk example@gmail.com
            _loginForm.value = LoginFormState(emailError = R.string.invalid_email)
        } else if (email.length > 255) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_email_max_length)
        } else if (password.length < 8) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password_min_length)
        } else if (password.length > 255) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password_max_length)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }
}