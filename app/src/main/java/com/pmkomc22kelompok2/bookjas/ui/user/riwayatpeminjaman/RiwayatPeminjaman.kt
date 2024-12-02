package com.pmkomc22kelompok2.bookjas.ui.user.riwayatpeminjaman

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RiwayatPeminjaman(
    val foto: Int,
    val judulBuku: String,
    val author: String,
    val tanggalPeminjaman: String,
    val tanggalPengembalian: String,
) : Parcelable