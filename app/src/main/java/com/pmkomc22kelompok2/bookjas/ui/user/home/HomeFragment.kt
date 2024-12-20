package com.pmkomc22kelompok2.bookjas.ui.user.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.pmkomc22kelompok2.bookjas.R
import com.pmkomc22kelompok2.bookjas.databinding.FragmentHomeBinding
import com.pmkomc22kelompok2.bookjas.ui.login.data.LoginRepository.UserManager.user

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val listBukuBaruDipinjam = ArrayList<BukuBaruDiPinjam>()
    private val listRekomendasiBuku = ArrayList<RekomendasiBuku>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        /*val textView: TextView = binding.textSelamatDatang
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }*/
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.nama.text = user?.nama

        binding.rvBukuYangBaruDipinjam.setHasFixedSize(true)
        listBukuBaruDipinjam.addAll(getListBukuBaruDipinjam())

        binding.rvRekomendasiBuku.setHasFixedSize(true)
        listRekomendasiBuku.addAll(getListRekomendasiBuku())

        showRecyclerList()

        /*binding.btnToDashboardAdmin.setOnClickListener {
            Navigation.findNavController(view).apply {
                navigate(R.id.action_navigation_home_to_dashboardAdminFragment)
            }
        }*/

        /*binding.btnToRiwayatPeminjaman.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_navigation_home_to_riwayatPeminjamanFragment)
        }*/
    }

    private fun getListBukuBaruDipinjam(): ArrayList<BukuBaruDiPinjam> {
        val foto = resources.obtainTypedArray(R.array.foto)
        val judulBuku = resources.getStringArray(R.array.judul_buku)
        val author = resources.getStringArray(R.array.author)
        val tenggat = resources.getStringArray(R.array.tanggal_pengembalian)
        val listItem = ArrayList<BukuBaruDiPinjam>()

        try {
            for (i in judulBuku.indices) {
                val item = BukuBaruDiPinjam(foto.getResourceId(i, -1), judulBuku[i], author[i], tenggat[i])
                listItem.add(item)
            }
        } finally {
            foto.recycle()
        }

        return listItem
    }

    private fun getListRekomendasiBuku(): ArrayList<RekomendasiBuku> {
        val foto = resources.obtainTypedArray(R.array.foto)
        val judulBuku = resources.getStringArray(R.array.judul_buku)
        val author = resources.getStringArray(R.array.author)
        val listItem = ArrayList<RekomendasiBuku>()

        try {
            for (i in judulBuku.indices) {
                val item = RekomendasiBuku(foto.getResourceId(i, -1), judulBuku[i], author[i])
                listItem.add(item)
            }
        } finally {
            foto.recycle()
        }

        return listItem
    }

    private fun showRecyclerList() {
        binding.rvBukuYangBaruDipinjam.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val listBukuBaruDipinjamAdapter = ListBukuBaruDiPinjamAdapter(listBukuBaruDipinjam) { item ->
            Navigation.findNavController(binding.root).navigate(R.id.action_navigation_home_to_detailBukuFragment)
        }
        binding.rvBukuYangBaruDipinjam.adapter = listBukuBaruDipinjamAdapter

        binding.rvRekomendasiBuku.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val listRekomendasiBukuAdapter = ListRekomendasiBukuAdapter(listRekomendasiBuku) { item ->
            Navigation.findNavController(binding.root).navigate(R.id.action_navigation_home_to_detailBukuFragment)
        }
        binding.rvRekomendasiBuku.adapter = listRekomendasiBukuAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}