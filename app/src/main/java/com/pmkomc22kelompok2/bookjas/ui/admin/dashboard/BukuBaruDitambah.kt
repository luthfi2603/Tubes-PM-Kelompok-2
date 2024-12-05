package com.pmkomc22kelompok2.bookjas.ui.admin.dashboard

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BukuBaruDitambah(
    val foto: Int,
    val judulBuku: String,
    val author: String,
) : Parcelable