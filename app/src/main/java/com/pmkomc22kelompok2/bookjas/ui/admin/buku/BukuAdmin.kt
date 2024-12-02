package com.pmkomc22kelompok2.bookjas.ui.admin.buku

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BukuAdmin(
    val foto: Int,
    val judulBuku: String,
    val author: String,
    val deskripsi: String,
    val jumlahBuku: Int,
    val tahunTerbit: String,
    val penerbit: String,
) : Parcelable
