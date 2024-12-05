package com.pmkomc22kelompok2.bookjas.ui.user.home

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RekomendasiBuku(
    val foto: Int,
    val judulBuku: String,
    val author: String,
) : Parcelable