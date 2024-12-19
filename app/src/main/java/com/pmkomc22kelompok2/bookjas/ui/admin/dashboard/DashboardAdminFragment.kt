package com.pmkomc22kelompok2.bookjas.ui.admin.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.pmkomc22kelompok2.bookjas.R
import com.pmkomc22kelompok2.bookjas.databinding.FragmentDashboardAdminBinding

class DashboardAdminFragment : Fragment() {
    private lateinit var binding: FragmentDashboardAdminBinding
    private val list = ArrayList<BukuBaruDitambah>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDashboardAdminBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvBukuBaruDitambah.setHasFixedSize(true)
        list.addAll(getList())
        showRecyclerList()

        /*binding.btnToRiwayatPeminjaman.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_dashboardAdminFragment_to_riwayatPeminjamanAdminFragment)
        }*/
        /*binding.btnToBukuAdmin.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_dashboardAdminFragment_to_bukuAdminFragment)
        }*/
    }

    private fun getList(): ArrayList<BukuBaruDitambah> {
        val foto = resources.obtainTypedArray(R.array.foto)
        val judulBuku = resources.getStringArray(R.array.judul_buku)
        val author = resources.getStringArray(R.array.author)
        val listItem = ArrayList<BukuBaruDitambah>()

        try {
            for (i in judulBuku.indices) {
                val item = BukuBaruDitambah(foto.getResourceId(i, -1), judulBuku[i], author[i])
                listItem.add(item)
            }
        } finally {
            foto.recycle()
        }

        return listItem
    }

    private fun showRecyclerList() {
        binding.rvBukuBaruDitambah.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val listAdapter = ListBukuBaruDitambahAdapter(list)
        binding.rvBukuBaruDitambah.adapter = listAdapter
    }
}