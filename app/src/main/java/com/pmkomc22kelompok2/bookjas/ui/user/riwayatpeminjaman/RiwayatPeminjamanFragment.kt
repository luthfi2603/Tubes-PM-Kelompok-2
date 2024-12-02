package com.pmkomc22kelompok2.bookjas.ui.user.riwayatpeminjaman

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.pmkomc22kelompok2.bookjas.R
import com.pmkomc22kelompok2.bookjas.databinding.FragmentRiwayatPeminjamanBinding

class RiwayatPeminjamanFragment : Fragment() {
    private lateinit var binding: FragmentRiwayatPeminjamanBinding
    private val list = ArrayList<RiwayatPeminjaman>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRiwayatPeminjamanBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvRiwayatPeminjaman.setHasFixedSize(true)
        list.addAll(getList())
        showRecyclerList()
    }

    private fun getList(): ArrayList<RiwayatPeminjaman> {
        val listItem = ArrayList<RiwayatPeminjaman>()

        val foto = resources.obtainTypedArray(R.array.foto)
        val judulBuku = resources.getStringArray(R.array.judul_buku)
        val author = resources.getStringArray(R.array.author)
        val tanggalPeminjaman = resources.getStringArray(R.array.tanggal_peminjaman)
        val tanggalPengembalian = resources.getStringArray(R.array.tanggal_pengembalian)

        try {
            for (i in judulBuku.indices) {
                val item = RiwayatPeminjaman(
                    foto.getResourceId(i, -1),
                    judulBuku[i],
                    author[i],
                    tanggalPeminjaman[i],
                    tanggalPengembalian[i]
                )
                listItem.add(item)
            }
        } finally {
            foto.recycle()
        }

        return listItem
    }

    private fun showRecyclerList() {
        binding.rvRiwayatPeminjaman.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val listAdapter = ListRiwayatPeminjamanAdapter(list)
        binding.rvRiwayatPeminjaman.adapter = listAdapter
    }
}