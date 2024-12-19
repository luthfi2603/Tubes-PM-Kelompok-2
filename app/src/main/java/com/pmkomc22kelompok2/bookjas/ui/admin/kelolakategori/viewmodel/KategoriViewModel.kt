package com.pmkomc22kelompok2.bookjas.ui.admin.kelolakategori.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pmkomc22kelompok2.bookjas.api.ApiClient
import com.pmkomc22kelompok2.bookjas.ui.admin.kelolakategori.Kategori
import com.pmkomc22kelompok2.bookjas.ui.user.dashboard.ListKategoriResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KategoriViewModel : ViewModel() {
    // LiveData untuk daftar kategori
    private val _listKategori = MutableLiveData<ArrayList<Kategori>>()
    val listKategori: LiveData<ArrayList<Kategori>>
        get() = _listKategori

    // LiveData untuk status loading atau error
    public val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?>
        get() = _errorMessage

    init {
        fetchKategori()
    }

    fun fetchKategori() {
        _isLoading.value = true
        _errorMessage.value = null

        ApiClient.apiService.getKategori().enqueue(object : Callback<ListKategoriResponse> {
            override fun onResponse(
                call: Call<ListKategoriResponse>,
                response: Response<ListKategoriResponse>
            ) {
                if (response.isSuccessful) {
                    val items = response.body()?.data
                    val listItem = ArrayList<Kategori>()

                    items?.forEach { item ->
                        val data = Kategori(item.kategori)
                        listItem.add(data)
                    }

                    _listKategori.value = listItem
                } else {
                    _errorMessage.value = "Failed to fetch categories: ${response.message()}"
                }
                _isLoading.value = false
            }

            override fun onFailure(call: Call<ListKategoriResponse>, t: Throwable) {
                _isLoading.value = false
                _errorMessage.value = t.message
            }
        })
    }
}