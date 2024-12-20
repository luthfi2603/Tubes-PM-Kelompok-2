package com.pmkomc22kelompok2.bookjas.ui.admin.riwayatpeminjaman

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pmkomc22kelompok2.bookjas.api.ApiClient
import com.pmkomc22kelompok2.bookjas.ui.login.data.LoginRepository.UserManager.user
import com.pmkomc22kelompok2.bookjas.ui.user.riwayatpeminjaman.RiwayatPeminjamanResponse
import com.pmkomc22kelompok2.bookjas.ui.user.riwayatpeminjaman.RiwayatPeminjamanResponseData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RiwayatPeminjamanAdminViewModel : ViewModel() {
    // LiveData untuk daftar peminjaman
    private val _listPeminjaman = MutableLiveData<ArrayList<RiwayatPeminjamanResponseData>>()
    val listPeminjaman: LiveData<ArrayList<RiwayatPeminjamanResponseData>>
        get() = _listPeminjaman

    // LiveData untuk status loading atau error
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?>
        get() = _errorMessage

    init {
        fetchPeminjaman()
    }

    fun fetchPeminjaman() {
        _isLoading.value = true
        _errorMessage.value = null

        ApiClient.apiService.getPeminjaman(user?.token).enqueue(object : Callback<RiwayatPeminjamanResponse> {
            override fun onResponse(
                call: Call<RiwayatPeminjamanResponse>,
                response: Response<RiwayatPeminjamanResponse>
            ) {
                if (response.isSuccessful) {
                    val items = response.body()?.data
                    val listItem = ArrayList<RiwayatPeminjamanResponseData>()

                    items?.forEach { item ->
                        val data = RiwayatPeminjamanResponseData(item.user_id, item.isbn, item.sampul, item.judul, item.penulis, item.peminjam, item.tanggal_peminjaman, item.tanggal_pengembalian, item.status, item.hari_tersisa, item.tenggat)
                        listItem.add(data)
                    }

                    _listPeminjaman.value = listItem
                } else {
                    _errorMessage.value = "Failed to fetch categories: ${response.message()}"
                }
                _isLoading.value = false
            }

            override fun onFailure(call: Call<RiwayatPeminjamanResponse>, t: Throwable) {
                _isLoading.value = false
                _errorMessage.value = t.message
            }
        })
    }
}