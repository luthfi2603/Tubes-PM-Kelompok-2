package com.pmkomc22kelompok2.bookjas.ui.user.buku

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.pmkomc22kelompok2.bookjas.api.ApiClient.UserManager.BASE_URL
import com.pmkomc22kelompok2.bookjas.databinding.FragmentDetailBukuBinding
import com.pmkomc22kelompok2.bookjas.ui.admin.buku.BukuAdminResponseData

class DetailBukuFragment : Fragment() {
    private lateinit var binding: FragmentDetailBukuBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBukuBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bukuData = arguments?.getParcelable<BukuAdminResponseData>("bukuData")

        Glide.with(requireContext())
            .load(BASE_URL + "/storage/" + bukuData?.sampul) // URL Gambar
            .into(binding.ivFotoBuku); /* imageView mana yang akan diterapkan */ binding.judulBuku.text = bukuData?.judul
        binding.penulisBuku.text = bukuData?.penulis
        binding.isbn2.text = bukuData?.isbn
        binding.jumlahBuku.text = bukuData?.jumlah_tersedia.toString()
        binding.nilaiTahunTerbit.text = bukuData?.tahun_terbit
        binding.nilaiTerbit.text = bukuData?.penerbit
        binding.nilaiDeskripsi.text = bukuData?.deskripsi
    }
}