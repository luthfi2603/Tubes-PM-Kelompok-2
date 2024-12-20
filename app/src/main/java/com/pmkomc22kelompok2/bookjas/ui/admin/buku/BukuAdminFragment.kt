package com.pmkomc22kelompok2.bookjas.ui.admin.buku

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.pmkomc22kelompok2.bookjas.R
import com.pmkomc22kelompok2.bookjas.databinding.FragmentBukuAdminBinding
import com.pmkomc22kelompok2.bookjas.ui.user.dashboard.KategoriData
import com.pmkomc22kelompok2.bookjas.ui.user.dashboard.ListKategoriAdapter

class BukuAdminFragment : Fragment() {
    private lateinit var binding: FragmentBukuAdminBinding
    private var list: ArrayList<BukuAdminResponseData>? = ArrayList()
    private val listKategori = ArrayList<KategoriData>()
    private val bukuAdminViewModel: BukuAdminViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBukuAdminBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loading.visibility = View.VISIBLE
        binding.vOverlay.visibility = View.VISIBLE

        bukuAdminViewModel.isLoading.observe(requireActivity()) { isLoading ->
            if (!isLoading) {
                binding.rvBooks.setHasFixedSize(true)
                bukuAdminViewModel.listBuku.observe(requireActivity()) { listBuku ->
                    list = listBuku
                }
                binding.loading.visibility = View.GONE
                binding.vOverlay.visibility = View.GONE
                showRecyclerList()
            }
        }

        if (!bukuAdminViewModel.isLoading.value!!) {
            binding.rvBooks.setHasFixedSize(true)
            bukuAdminViewModel.listBuku.observe(requireActivity()) { listBuku ->
                list = listBuku
            }
            binding.loading.visibility = View.GONE
            binding.vOverlay.visibility = View.GONE
            showRecyclerList()
        }

        binding.rvKategoriBuku.setHasFixedSize(true)
        listKategori.addAll(getListKategori())

        showRecyclerList()

        binding.btnTambahBuku.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_bukuAdminFragment_to_tambahBukuFragment)
        }

        binding.btnKelolaKategori.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_bukuAdminFragment_to_kelolaKategoriFragment)
        }
    }

    private fun getListKategori(): ArrayList<KategoriData> {
        val kategori = resources.getStringArray(R.array.kategori)
        val listItem = ArrayList<KategoriData>()

        for (i in kategori.indices) {
            val item = KategoriData(kategori[i])
            listItem.add(item)
        }

        return listItem
    }

    private fun showRecyclerList() {
        binding.rvBooks.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val listAdapter = ListBukuAdminAdapter(list!!) { item ->
            Navigation.findNavController(binding.root).navigate(R.id.action_bukuAdminFragment_to_editBukuFragment)
        }

        binding.rvKategoriBuku.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val listKategoriAdapter = ListKategoriAdapter(listKategori)
        binding.rvKategoriBuku.adapter = listKategoriAdapter

        binding.rvBooks.adapter = listAdapter
    }
}