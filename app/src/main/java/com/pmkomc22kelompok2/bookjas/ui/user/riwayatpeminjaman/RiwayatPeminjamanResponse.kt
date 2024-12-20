package com.pmkomc22kelompok2.bookjas.ui.user.riwayatpeminjaman

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class RiwayatPeminjamanResponse(
    val data: ArrayList<RiwayatPeminjamanResponseData>
)

@Parcelize
data class RiwayatPeminjamanResponseData(
    val user_id: String,
    val isbn: String,
    val sampul: String,
    val judul: String,
    val penulis: String,
    val peminjam: String,
    val tanggal_peminjaman: String,
    val tanggal_pengembalian: String?,
    val status: String,
    val hari_tersisa: String,
    val tenggat: String,
) : Parcelable