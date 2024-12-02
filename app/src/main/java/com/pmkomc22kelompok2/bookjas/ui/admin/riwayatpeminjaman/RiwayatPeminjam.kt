package com.pmkomc22kelompok2.bookjas.ui.admin.riwayatpeminjaman

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RiwayatPeminjam(
    val foto: Int,
    val judulBuku: String,
    val author: String,
    val peminjam: String,
    val tanggalPeminjaman: String,
    val tanggalPengembalian: String,
) : Parcelable