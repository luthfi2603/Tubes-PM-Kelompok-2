package com.pmkomc22kelompok2.bookjas.ui.admin.buku

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.pmkomc22kelompok2.bookjas.R
import com.pmkomc22kelompok2.bookjas.databinding.FragmentBukuAdminBinding

class BukuAdminFragment : Fragment() {
    private lateinit var binding: FragmentBukuAdminBinding
    private val list = ArrayList<BukuAdmin>()

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

        binding.rvBooks.setHasFixedSize(true)
        list.addAll(getList())
        showRecyclerList()
    }

    private fun getList(): ArrayList<BukuAdmin> {
        val listItem = ArrayList<BukuAdmin>()

        val foto = resources.obtainTypedArray(R.array.foto)
        val judulBuku = resources.getStringArray(R.array.judul_buku)
        val author = resources.getStringArray(R.array.author)
        val deskripsi = resources.getStringArray(R.array.deskripsi)
        val jumlahBuku = resources.getIntArray(R.array.jumlah_buku)
        val tahunTerbit = resources.getStringArray(R.array.tahun_terbit)
        val penerbit = resources.getStringArray(R.array.penerbit)

        try {
            for (i in judulBuku.indices) {
                val item = BukuAdmin(
                    foto.getResourceId(i, -1),
                    judulBuku[i],
                    author[i],
                    deskripsi[i],
                    jumlahBuku[i],
                    tahunTerbit[i],
                    penerbit[i]
                )
                listItem.add(item)
            }
        } finally {
            foto.recycle()
        }

        return listItem
    }

    private fun showRecyclerList() {
        binding.rvBooks.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val listAdapter = ListBukuAdminAdapter(list)
        binding.rvBooks.adapter = listAdapter
    }
}