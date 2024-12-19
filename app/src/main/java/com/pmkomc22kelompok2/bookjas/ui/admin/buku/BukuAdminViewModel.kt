package com.pmkomc22kelompok2.bookjas.ui.admin.buku

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pmkomc22kelompok2.bookjas.api.ApiClient
import com.pmkomc22kelompok2.bookjas.ui.login.data.LoginRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BukuAdminViewModel : ViewModel() {
    // LiveData untuk daftar kategori
    private val _listBuku = MutableLiveData<ArrayList<BukuAdminResponseData>>()
    val listBuku: LiveData<ArrayList<BukuAdminResponseData>>
        get() = _listBuku

    // LiveData untuk status loading atau error
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?>
        get() = _errorMessage

    init {
        fetchBuku()
    }

    private fun fetchBuku() {
        _isLoading.value = true
        _errorMessage.value = null

        ApiClient.apiService.getBooks(LoginRepository.UserManager.user?.token).enqueue(object : Callback<BukuAdminResponse> {
            override fun onResponse(
                call: Call<BukuAdminResponse>,
                response: Response<BukuAdminResponse>
            ) {
                if (response.isSuccessful) {
                    val items = response.body()?.data
                    val listItem = ArrayList<BukuAdminResponseData>()

                    items?.forEach { item ->
                        val data = BukuAdminResponseData(item.isbn, item.sampul, item.judul, item.kategori, item.penulis, item.penerbit, item.deskripsi, item.tahun_terbit, item.jumlah_tersedia)
                        listItem.add(data)
                    }

                    _listBuku.value = listItem
                } else {
                    _errorMessage.value = "Failed to fetch categories: ${response.message()}"
                }
                _isLoading.value = false
            }

            override fun onFailure(call: Call<BukuAdminResponse>, t: Throwable) {
                _isLoading.value = false
                _errorMessage.value = t.message
            }
        })
    }
}