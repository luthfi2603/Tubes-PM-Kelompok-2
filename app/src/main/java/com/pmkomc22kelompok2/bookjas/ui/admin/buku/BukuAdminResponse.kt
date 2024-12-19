package com.pmkomc22kelompok2.bookjas.ui.admin.buku

import com.pmkomc22kelompok2.bookjas.ui.user.dashboard.KategoriData

data class BukuAdminResponse(
    val data: ArrayList<BukuAdminResponseData>
)

data class BukuAdminResponseData(
    val isbn: String,
    val sampul: String,
    val judul: String,
    val kategori: String,
    val penulis: String,
    val penerbit: String,
    val deskripsi: String,
    val tahun_terbit: String,
    val jumlah_tersedia: Int,
)