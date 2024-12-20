package com.pmkomc22kelompok2.bookjas.ui.user.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.pmkomc22kelompok2.bookjas.R
import com.pmkomc22kelompok2.bookjas.databinding.FragmentHomeBinding
import com.pmkomc22kelompok2.bookjas.ui.admin.buku.BukuAdminResponseData
import com.pmkomc22kelompok2.bookjas.ui.admin.buku.BukuAdminViewModel
import com.pmkomc22kelompok2.bookjas.ui.login.data.LoginRepository.UserManager.user
import com.pmkomc22kelompok2.bookjas.ui.user.riwayatpeminjaman.RiwayatPeminjamanResponseData
import com.pmkomc22kelompok2.bookjas.ui.user.riwayatpeminjaman.RiwayatPeminjamanViewModel
import java.util.Locale

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var listBukuBaruDipinjam = ArrayList<RiwayatPeminjamanResponseData>()
    private var listRekomendasiBuku = ArrayList<BukuAdminResponseData>()
    private val bukuAdminViewModel: BukuAdminViewModel by viewModels()
    private val peminjamanViewModel: RiwayatPeminjamanViewModel by viewModels()

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

        binding.loadingDipinjam.visibility = View.VISIBLE
        binding.vOverlayDipinjam.visibility = View.VISIBLE

        peminjamanViewModel.isLoading.observe(requireActivity()) { isLoading ->
            if (!isLoading) {
                binding.rvBukuYangBaruDipinjam.setHasFixedSize(true)
                peminjamanViewModel.listPeminjaman.observe(requireActivity()) { listPeminjaman ->
                    listBukuBaruDipinjam = ArrayList(listPeminjaman.take(7))
                }
                binding.loadingDipinjam.visibility = View.GONE
                binding.vOverlayDipinjam.visibility = View.GONE
                showRecyclerList()
            }
        }

        if (!peminjamanViewModel.isLoading.value!!) {
            binding.rvBukuYangBaruDipinjam.setHasFixedSize(true)
            peminjamanViewModel.listPeminjaman.observe(requireActivity()) { listPeminjaman ->
                listBukuBaruDipinjam = ArrayList(listPeminjaman.take(7))
            }
            binding.loadingDipinjam.visibility = View.GONE
            binding.vOverlayDipinjam.visibility = View.GONE
            showRecyclerList()
        }

        binding.loadingBuku.visibility = View.VISIBLE
        binding.vOverlayBuku.visibility = View.VISIBLE

        bukuAdminViewModel.isLoading.observe(requireActivity()) { isLoading ->
            if (!isLoading) {
                binding.rvRekomendasiBuku.setHasFixedSize(true)
                bukuAdminViewModel.listBuku.observe(requireActivity()) { listBuku ->
                    val sortedBooks = listBuku.sortedByDescending { it.tahun_terbit.toIntOrNull() ?: 0 }

                    listRekomendasiBuku = ArrayList(sortedBooks.take(7))
                }
                binding.loadingBuku.visibility = View.GONE
                binding.vOverlayBuku.visibility = View.GONE
                showRecyclerList()
            }
        }

        if (!bukuAdminViewModel.isLoading.value!!) {
            binding.rvRekomendasiBuku.setHasFixedSize(true)
            bukuAdminViewModel.listBuku.observe(requireActivity()) { listBuku ->
                val sortedBooks = listBuku.sortedByDescending { it.tahun_terbit.toIntOrNull() ?: 0 }

                listRekomendasiBuku = ArrayList(sortedBooks.take(7))
            }
            binding.loadingBuku.visibility = View.GONE
            binding.vOverlayBuku.visibility = View.GONE
            showRecyclerList()
        }

        /*binding.btnToDashboardAdmin.setOnClickListener {
            Navigation.findNavController(view).apply {
                navigate(R.id.action_navigation_home_to_dashboardAdminFragment)
            }
        }*/

        /*binding.btnToRiwayatPeminjaman.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_navigation_home_to_riwayatPeminjamanFragment)
        }*/
    }

    private fun showRecyclerList() {
        binding.rvBukuYangBaruDipinjam.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val listBukuBaruDipinjamAdapter = ListBukuBaruDiPinjamAdapter(listBukuBaruDipinjam)/* { item ->
            val bundle = Bundle().apply {
                putParcelable("bukuData", item)
            }
            Navigation.findNavController(binding.root).navigate(R.id.action_navigation_home_to_detailBukuFragment, bundle)
        }*/
        if (peminjamanViewModel.listPeminjaman.value.isNullOrEmpty()) {
            binding.rvBukuYangBaruDipinjam.visibility = View.INVISIBLE
            binding.tvPeminjamanKosong.visibility = View.VISIBLE

            binding.titleBukuYangBaruDipinjam.setPadding(
                binding.titleBukuYangBaruDipinjam.paddingLeft,
                binding.titleBukuYangBaruDipinjam.paddingTop,
                binding.titleBukuYangBaruDipinjam.paddingRight,
                16
            )
        }
        binding.rvBukuYangBaruDipinjam.adapter = listBukuBaruDipinjamAdapter

        binding.rvRekomendasiBuku.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val listRekomendasiBukuAdapter = ListRekomendasiBukuAdapter(listRekomendasiBuku) { item ->
            val bundle = Bundle().apply {
                putParcelable("bukuData", item)
            }
            Navigation.findNavController(binding.root).navigate(R.id.action_navigation_home_to_detailBukuFragment, bundle)
        }
        binding.rvRekomendasiBuku.adapter = listRekomendasiBukuAdapter

        binding.jumlah.text = String.format(Locale.getDefault(), "%d buku", listBukuBaruDipinjam.size)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}