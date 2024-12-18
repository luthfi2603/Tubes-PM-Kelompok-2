package com.pmkomc22kelompok2.bookjas.ui.login.data

import com.pmkomc22kelompok2.bookjas.ui.login.data.model.UserLoginRequest
import com.pmkomc22kelompok2.bookjas.ui.login.data.model.UserLoginResponse
import com.pmkomc22kelompok2.bookjas.ui.login.data.model.UserLoginResponseData

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LoginRepository(val dataSource: LoginDataSource) {

    // in-memory cache of the loggedInUser object
    var user: UserLoginResponseData? = null
        private set

    val isLoggedIn: Boolean
        get() = user != null

    init {
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
        user = null
    }

    fun logout() {
        user = null
        dataSource.logout()
    }

    // fun login(email: String, password: String): Result<UserLoginResponseData> {
        // handle login
        /*val result = dataSource.login(email, password)

        if (result is Result.Success) {
            setLoggedInUser(result.data)
        }

        return result*/

        /*lateinit var item: Result<UserLoginResponseData>
        dataSource.login(email, password) { result ->
            if (result is Result.Success) {
                setLoggedInUser(result.data)
                item = result
            }
        }
        return item
    }*/

    fun login(email: String, password: String, callback: (Result<UserLoginResponseData>) -> Unit) {
        dataSource.login(email, password) { result ->
            if (result is Result.Success) {
                // Menyimpan data user setelah login berhasil
                setLoggedInUser(result.data)
            }
            // Mengirimkan hasil login melalui callback
            callback(result)
        }
    }

    private fun setLoggedInUser(loggedInUser: UserLoginResponseData) {
        this.user = loggedInUser
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }
}