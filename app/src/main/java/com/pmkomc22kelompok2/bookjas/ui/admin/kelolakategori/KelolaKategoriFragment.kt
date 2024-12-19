package com.pmkomc22kelompok2.bookjas.ui.admin.kelolakategori

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.pmkomc22kelompok2.bookjas.R
import com.pmkomc22kelompok2.bookjas.databinding.FragmentKelolaKategoriBinding
import com.pmkomc22kelompok2.bookjas.ui.admin.kelolakategori.viewmodel.KategoriViewModel

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
        val listAdapter = ListKategoriAdapter(list!!) { item ->
            Navigation.findNavController(binding.root).navigate(R.id.action_kelolaKategoriFragment_to_editKategoriFragment)
        }
        binding.rvKategoriBuku.adapter = listAdapter
    }
}