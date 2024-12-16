package com.pmkomc22kelompok2.bookjas.ui.user.register.ui.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pmkomc22kelompok2.bookjas.ui.user.register.data.LoginDataSource
import com.pmkomc22kelompok2.bookjas.ui.user.register.data.LoginRepository

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
class RegisterViewModelFactory : ViewModelProvider.Factory {
    /*@Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            return RegisterViewModel(
                loginRepository = LoginRepository(
                    dataSource = LoginDataSource()
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }*/
}