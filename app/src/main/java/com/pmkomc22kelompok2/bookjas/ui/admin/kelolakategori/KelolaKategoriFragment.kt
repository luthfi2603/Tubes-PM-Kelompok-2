package com.pmkomc22kelompok2.bookjas.ui.admin.kelolakategori

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
import com.pmkomc22kelompok2.bookjas.databinding.FragmentKelolaKategoriBinding
import com.pmkomc22kelompok2.bookjas.ui.admin.kelolakategori.viewmodel.KategoriViewModel
import com.pmkomc22kelompok2.bookjas.ui.admin.tambahkategori.TambahKategoriResponseError
import com.pmkomc22kelompok2.bookjas.ui.login.data.LoginRepository.UserManager.user
import com.pmkomc22kelompok2.bookjas.ui.user.edit.profile.EditProfileErrorResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KelolaKategoriFragment : Fragment() {
    private lateinit var binding: FragmentKelolaKategoriBinding
    private var list: ArrayList<Kategori>? = ArrayList()
    private val kategoriViewModel: KategoriViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentKelolaKategoriBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        kategoriViewModel.isLoading.observe(requireActivity()) { isLoading ->
            if (!isLoading) {
                binding.rvKategoriBuku.setHasFixedSize(true)
                kategoriViewModel.listKategori.observe(requireActivity()) { listKategori ->
                    list = listKategori
                }
                showRecyclerList()
            }
        }

        if (!kategoriViewModel.isLoading.value!!) {
            binding.rvKategoriBuku.setHasFixedSize(true)
            kategoriViewModel.listKategori.observe(requireActivity()) { listKategori ->
                list = listKategori
            }
            showRecyclerList()
        }

        binding.btnTambahKategori.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_kelolaKategoriFragment_to_tambahKategoriFragment)
        }
    }

    private fun showRecyclerList() {
        binding.rvKategoriBuku.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val listAdapter = ListKategoriAdapter(list!!, onEditClick = { item ->
            val bundle = Bundle().apply {
                putParcelable("kategori", item)
            }
            Navigation.findNavController(binding.root).navigate(R.id.action_kelolaKategoriFragment_to_editKategoriFragment, bundle)
        }, onDeleteClick = { item ->
            binding.loading.visibility = View.VISIBLE
            binding.vOverlay.visibility = View.VISIBLE

            // Panggil API melalui ApiClient
            ApiClient.apiService.deleteKategori(item.kategori, user?.token).enqueue(object : Callback<OkResponse> {
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
                        kategoriViewModel.fetchKategori()
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
        })
        binding.rvKategoriBuku.adapter = listAdapter
    }
}