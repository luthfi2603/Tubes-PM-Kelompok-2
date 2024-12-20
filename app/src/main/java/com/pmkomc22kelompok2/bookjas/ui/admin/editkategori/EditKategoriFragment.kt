package com.pmkomc22kelompok2.bookjas.ui.admin.editkategori

import android.content.Context
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
import com.pmkomc22kelompok2.bookjas.databinding.FragmentEditKategoriBinding
import com.pmkomc22kelompok2.bookjas.ui.admin.kelolakategori.Kategori
import com.pmkomc22kelompok2.bookjas.ui.admin.tambahkategori.TambahKategoriResponseError
import com.pmkomc22kelompok2.bookjas.ui.login.data.LoginRepository.UserManager.user
import com.pmkomc22kelompok2.bookjas.ui.user.edit.profile.EditProfileErrorResponse
import com.pmkomc22kelompok2.bookjas.ui.user.edit.profile.EditProfileRequest
import com.pmkomc22kelompok2.bookjas.ui.user.register.data.model.UserRegisterRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditKategoriFragment : Fragment() {
    private lateinit var binding: FragmentEditKategoriBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditKategoriBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val kategoriData = arguments?.getParcelable<Kategori>("kategori")

        binding.etKategoriBuku.setText(kategoriData?.kategori)

        binding.btnEditKategori.setOnClickListener {
            val kategori = binding.etKategoriBuku.text.toString()

            binding.loading.visibility = View.VISIBLE
            binding.vOverlay.visibility = View.VISIBLE

            // Buat data permintaan untuk API
            val request = Kategori(
                kategori = kategori
            )

            // Panggil API melalui ApiClient
            ApiClient.apiService.updateKategori(kategoriData?.kategori, user?.token, request).enqueue(object : Callback<OkResponse> {
                override fun onResponse(
                    call: Call<OkResponse>,
                    response: Response<OkResponse>
                ) {
                    binding.loading.visibility = View.GONE
                    binding.vOverlay.visibility = View.GONE
                    if (response.isSuccessful) {
                        Navigation.findNavController(view).navigate(R.id.action_editKategoriFragment_to_kelolaKategoriFragment)

                        Toast.makeText(
                            context,
                            response.body()?.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        val errorBody = response.errorBody()?.string()
                        val errorMessage = errorBody?.let {
                            try {
                                val errorResponse = Gson().fromJson(it, TambahKategoriResponseError::class.java)
                                errorResponse.errors.kategori[0]
                            } catch (e: Exception) {
                                Log.e("EDIT_PROFILE", "Failed to parse error message", e)
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