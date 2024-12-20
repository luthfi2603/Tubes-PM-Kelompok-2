package com.pmkomc22kelompok2.bookjas.ui.admin.riwayatpeminjaman

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.pmkomc22kelompok2.bookjas.R
import com.pmkomc22kelompok2.bookjas.api.ApiClient
import com.pmkomc22kelompok2.bookjas.data.ErrorResponse
import com.pmkomc22kelompok2.bookjas.data.OkResponse
import com.pmkomc22kelompok2.bookjas.databinding.FragmentRiwayatPeminjamanAdminBinding
import com.pmkomc22kelompok2.bookjas.ui.login.data.LoginRepository.UserManager.user
import com.pmkomc22kelompok2.bookjas.ui.user.riwayatpeminjaman.RiwayatPeminjamanResponseData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RiwayatPeminjamanAdminFragment : Fragment() {
    private lateinit var binding: FragmentRiwayatPeminjamanAdminBinding
    private var listPeminjam = ArrayList<RiwayatPeminjamanResponseData>()
    private val tambahPeminjamanViewModel: RiwayatPeminjamanAdminViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRiwayatPeminjamanAdminBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loading.visibility = View.VISIBLE
        binding.vOverlay.visibility = View.VISIBLE

        tambahPeminjamanViewModel.isLoading.observe(requireActivity()) { isLoading ->
            if (!isLoading) {
                binding.rvRiwayatPeminjaman.setHasFixedSize(true)
                tambahPeminjamanViewModel.listPeminjaman.observe(requireActivity()) { listPeminjaman ->
                    listPeminjam = listPeminjaman
                }
                binding.loading.visibility = View.GONE
                binding.vOverlay.visibility = View.GONE
                showRecyclerList()
            }
        }

        if (!tambahPeminjamanViewModel.isLoading.value!!) {
            binding.rvRiwayatPeminjaman.setHasFixedSize(true)
            tambahPeminjamanViewModel.listPeminjaman.observe(requireActivity()) { listPeminjaman ->
                listPeminjam = listPeminjaman
            }
            binding.loading.visibility = View.GONE
            binding.vOverlay.visibility = View.GONE
            showRecyclerList()
        }

        binding.btnTambahPeminjaman.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_riwayatPeminjamanAdminFragment_to_tambahPeminjamanFragment)
        }
    }

    private fun showRecyclerList() {
        binding.rvRiwayatPeminjaman.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val listRiwayatPeminjamAdapter = ListRiwayatPeminjamAdapter(listPeminjam) { item ->
            binding.loading.visibility = View.VISIBLE
            binding.vOverlay.visibility = View.VISIBLE

            // Panggil API melalui ApiClient
            ApiClient.apiService.updatePeminjaman(item.user_id, item.isbn, user?.token).enqueue(object : Callback<OkResponse> {
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
                        tambahPeminjamanViewModel.fetchPeminjaman()
                    } else {
                        val errorBody = response.errorBody()?.string()
                        val errorMessage = errorBody?.let {
                            try {
                                val errorResponse = Gson().fromJson(it, ErrorResponse::class.java)
                                errorResponse.errors
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
        binding.rvRiwayatPeminjaman.adapter = listRiwayatPeminjamAdapter
    }
}