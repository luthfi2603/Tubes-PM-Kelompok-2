package com.pmkomc22kelompok2.bookjas.ui.admin.riwayatpeminjaman

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.pmkomc22kelompok2.bookjas.R
import com.pmkomc22kelompok2.bookjas.databinding.FragmentRiwayatPeminjamanAdminBinding

class RiwayatPeminjamanAdminFragment : Fragment() {
    private lateinit var binding: FragmentRiwayatPeminjamanAdminBinding
    private val listPeminjam = ArrayList<RiwayatPeminjam>()

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

        binding.rvRiwayatPeminjaman.setHasFixedSize(true)
        listPeminjam.addAll(getListRiwayatPeminjam())
        showRecyclerList()

        binding.btnTambahPeminjaman.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_riwayatPeminjamanAdminFragment_to_tambahPeminjamanFragment)
        }
    }

    private fun getListRiwayatPeminjam(): ArrayList<RiwayatPeminjam> {
        val listRiwayatPeminjam = ArrayList<RiwayatPeminjam>()

        val foto = resources.obtainTypedArray(R.array.foto)
        val judulBuku = resources.getStringArray(R.array.judul_buku)
        val author = resources.getStringArray(R.array.author)
        val peminjam = resources.getStringArray(R.array.peminjam)
        val tanggalPeminjaman = resources.getStringArray(R.array.tanggal_peminjaman)
        val tanggalPengembalian = resources.getStringArray(R.array.tanggal_pengembalian)

        try {
            for (i in judulBuku.indices) {
                val item = RiwayatPeminjam(foto.getResourceId(i, -1), judulBuku[i], author[i], peminjam[i], tanggalPeminjaman[i], tanggalPengembalian[i])
                listRiwayatPeminjam.add(item)
            }
        } finally {
            foto.recycle()
        }

        return listRiwayatPeminjam
    }

    private fun showRecyclerList() {
        binding.rvRiwayatPeminjaman.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val listRiwayatPeminjamAdapter = ListRiwayatPeminjamAdapter(listPeminjam) { item ->
            Navigation.findNavController(binding.root).navigate(R.id.action_riwayatPeminjamanAdminFragment_to_editPeminjamanFragment)
        }
        binding.rvRiwayatPeminjaman.adapter = listRiwayatPeminjamAdapter
    }
}