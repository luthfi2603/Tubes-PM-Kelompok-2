package com.pmkomc22kelompok2.bookjas.ui.login.data

import com.pmkomc22kelompok2.bookjas.api.ApiClient
import com.pmkomc22kelompok2.bookjas.ui.login.data.model.UserLoginRequest
import com.pmkomc22kelompok2.bookjas.ui.login.data.model.UserLoginResponse
import com.pmkomc22kelompok2.bookjas.ui.login.data.model.UserLoginResponseData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {
    /*fun login(email: String, password: String): Result<UserLoginResponseData> {
        lateinit var user: UserLoginResponseData
        try {
            // Buat data permintaan untuk API
            val request = UserLoginRequest(
                email = email,
                password = password,
            )

            // Panggil API melalui ApiClient
            ApiClient.apiService.login(request).enqueue(object : Callback<UserLoginResponse> {
                override fun onResponse(
                    call: Call<UserLoginResponse>,
                    response: Response<UserLoginResponse>
                ) {
                    if (response.isSuccessful) {
                        val item = response.body()?.data
                        user = UserLoginResponseData(
                            item?.id,
                            item?.email,
                            item?.nama,
                            item?.foto_profil,
                            item?.token
                        )
                    } else {
                        println("Error: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<UserLoginResponse>, t: Throwable) {
                    println("Error: ${t.message}")
                }
            })

            println("Hasil response: $user")
            return Result.Success(user)
        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }*/

    fun login(email: String, password: String, callback: (Result<UserLoginResponseData>) -> Unit) {
        val request = UserLoginRequest(email = email, password = password)

        ApiClient.apiService.login(request).enqueue(object : Callback<UserLoginResponse> {
            override fun onResponse(call: Call<UserLoginResponse>, response: Response<UserLoginResponse>) {
                if (response.isSuccessful) {
                    val item = response.body()?.data
                    val user = UserLoginResponseData(
                        item?.id,
                        item?.email,
                        item?.nama,
                        item?.token,
                        item?.status,
                        password
                    )
                    callback(Result.Success(user))
                } else {
                    callback(Result.Error(IOException("Login failed: ${response.message()}")))
                }
            }

            override fun onFailure(call: Call<UserLoginResponse>, t: Throwable) {
                callback(Result.Error(IOException("Error logging in", t)))
            }
        })
    }

    fun logout() {
        // TODO: revoke authentication
    }
}