package com.pmkomc22kelompok2.bookjas.ui.user.riwayatpeminjaman

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.pmkomc22kelompok2.bookjas.databinding.FragmentRiwayatPeminjamanBinding
import com.pmkomc22kelompok2.bookjas.ui.login.data.LoginRepository.UserManager.user
import java.util.Locale

class RiwayatPeminjamanFragment : Fragment() {
    private lateinit var binding: FragmentRiwayatPeminjamanBinding
    private var list = ArrayList<RiwayatPeminjamanResponseData>()
    private val peminjamanViewModel: RiwayatPeminjamanViewModel by viewModels()

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

        binding.tvNama.text = user?.nama

        binding.loading.visibility = View.VISIBLE
        binding.vOverlay.visibility = View.VISIBLE

        peminjamanViewModel.isLoading.observe(requireActivity()) { isLoading ->
            if (!isLoading) {
                binding.rvRiwayatPeminjaman.setHasFixedSize(true)
                peminjamanViewModel.listPeminjaman.observe(requireActivity()) { listPeminjaman ->
                    list = listPeminjaman
                }
                binding.loading.visibility = View.GONE
                binding.vOverlay.visibility = View.GONE
                showRecyclerList()
            }
        }

        if (!peminjamanViewModel.isLoading.value!!) {
            binding.rvRiwayatPeminjaman.setHasFixedSize(true)
            peminjamanViewModel.listPeminjaman.observe(requireActivity()) { listPeminjaman ->
                list = listPeminjaman
            }
            binding.loading.visibility = View.GONE
            binding.vOverlay.visibility = View.GONE
            showRecyclerList()
        }
    }

    private fun showRecyclerList() {
        binding.rvRiwayatPeminjaman.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val listAdapter = ListRiwayatPeminjamanAdapter(list)
        if (peminjamanViewModel.listPeminjaman.value.isNullOrEmpty()) {
            binding.rvRiwayatPeminjaman.visibility = View.GONE
            binding.tvPeminjamanKosong.visibility = View.VISIBLE
        }
        binding.rvRiwayatPeminjaman.adapter = listAdapter

        binding.jumlah.text = String.format(Locale.getDefault(), "%d buku", list.size)
    }
}