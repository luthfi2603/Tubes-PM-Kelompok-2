package com.pmkomc22kelompok2.bookjas.ui.admin.kelolakategori

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.pmkomc22kelompok2.bookjas.R
import com.pmkomc22kelompok2.bookjas.databinding.FragmentKelolaKategoriBinding

class KelolaKategoriFragment : Fragment() {
    private lateinit var binding: FragmentKelolaKategoriBinding
    private val list = ArrayList<Kategori>()

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

        binding.rvKategoriBuku.setHasFixedSize(true)
        list.addAll(getList())
        showRecyclerList()

        binding.btnTambahKategori.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_kelolaKategoriFragment_to_tambahKategoriFragment)
        }
    }

    private fun getList(): ArrayList<Kategori> {
        val listItem = ArrayList<Kategori>()

        val kategori = resources.getStringArray(R.array.kategori)

        for (i in kategori.indices) {
            val item = Kategori(kategori[i])
            listItem.add(item)
        }

        return listItem
    }

    private fun showRecyclerList() {
        binding.rvKategoriBuku.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val listAdapter = ListKategoriAdapter(list) { item ->
            Navigation.findNavController(binding.root).navigate(R.id.action_kelolaKategoriFragment_to_editKategoriFragment)
        }
        binding.rvKategoriBuku.adapter = listAdapter
    }
}