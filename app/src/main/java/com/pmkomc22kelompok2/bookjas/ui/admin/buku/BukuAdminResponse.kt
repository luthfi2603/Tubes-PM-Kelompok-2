package com.pmkomc22kelompok2.bookjas.ui.admin.buku

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class BukuAdminResponse(
    val data: ArrayList<BukuAdminResponseData>
)

@Parcelize
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
) : Parcelable