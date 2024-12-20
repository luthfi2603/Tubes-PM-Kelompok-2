package com.pmkomc22kelompok2.bookjas.ui.admin.tambahpeminjaman

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.google.gson.Gson
import com.pmkomc22kelompok2.bookjas.R
import com.pmkomc22kelompok2.bookjas.api.ApiClient
import com.pmkomc22kelompok2.bookjas.data.ErrorResponse
import com.pmkomc22kelompok2.bookjas.data.OkResponse
import com.pmkomc22kelompok2.bookjas.databinding.FragmentTambahPeminjamanBinding
import com.pmkomc22kelompok2.bookjas.ui.admin.tambahkategori.TambahKategoriRequest
import com.pmkomc22kelompok2.bookjas.ui.admin.tambahkategori.TambahKategoriResponseError
import com.pmkomc22kelompok2.bookjas.ui.login.data.LoginRepository.UserManager.user
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.tan

class TambahPeminjamanFragment : Fragment() {
    private lateinit var binding: FragmentTambahPeminjamanBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTambahPeminjamanBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnTambahPeminjaman.setOnClickListener {
            // val tanggal = binding.etTanggalPeminjaman.text.toString()
            val isbn = binding.etJudulBuku.text.toString()
            val email = binding.etEmailPeminjam.text.toString()

            binding.loading.visibility = View.VISIBLE
            binding.vOverlay.visibility = View.VISIBLE

            // Buat data permintaan untuk API
            val request = TambahPeminjamanRequest(
                email = email,
                isbn = isbn,
                // tanggal_peminjaman = tanggal
            )

            // Panggil API melalui ApiClient
            ApiClient.apiService.storePeminjaman(request, user?.token).enqueue(object : Callback<OkResponse> {
                override fun onResponse(
                    call: Call<OkResponse>,
                    response: Response<OkResponse>
                ) {
                    binding.loading.visibility = View.GONE
                    binding.vOverlay.visibility = View.GONE
                    if (response.isSuccessful) {
                        Toast.makeText(
                            context,
                            response.body()?.message,
                            Toast.LENGTH_SHORT
                        ).show()
                        Navigation.findNavController(view).navigate(R.id.action_tambahPeminjamanFragment_to_riwayatPeminjamanAdminFragment)
                    } else {
                        val errorBody = response.errorBody()?.string()
                        val errorMessage = errorBody?.let {
                            try {
                                val errorResponse = Gson().fromJson(it, TambahPeminjamanResponseError::class.java)

                                // Periksa apakah field tertentu memiliki error
                                val emailError = errorResponse.errors.email?.get(0)
                                val isbnError = errorResponse.errors.isbn?.get(0)
                                val messageError = errorResponse.errors.message?.get(0)

                                // Bangun pesan error berdasarkan response
                                val errorMessages = mutableListOf<String>()
                                if (emailError?.isNotEmpty() == true) errorMessages.add("Email: $emailError")
                                if (isbnError?.isNotEmpty() == true) errorMessages.add("ISBN: $isbnError")
                                if (messageError != null) errorMessages.add("Error: $messageError")

                                // Gabungkan pesan error
                                errorMessages.joinToString("\n")
                            } catch (e: Exception) {
                                Log.e("POST", "Failed to parse error message", e)
                                "Failed to parse error message"
                            }
                        } ?: "Unknown error occurred"
                        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<OkResponse>, t: Throwable) {
                    binding.loading.visibility = View.GONE
                    binding.vOverlay.visibility = View.GONE
                    Toast.makeText(context, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}