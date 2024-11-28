package com.pmkomc22kelompok2.bookjas.ui.dashboard

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Book(
    val foto: Int,
    val jumlahBuku: Int,
    val judulBuku: String,
    val author: String,
) : Parcelable