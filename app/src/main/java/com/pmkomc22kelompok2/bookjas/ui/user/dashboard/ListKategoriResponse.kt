package com.pmkomc22kelompok2.bookjas.ui.user.dashboard

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class ListKategoriResponse (
    val data: ArrayList<KategoriData>
)

@Parcelize
data class KategoriData(
    val kategori: String
) : Parcelable