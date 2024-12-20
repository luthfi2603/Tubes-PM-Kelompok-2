package com.pmkomc22kelompok2.bookjas.ui.admin.tambahkategori

data class TambahKategoriResponseError(
    val errors: TambahKategoriResponseErrorData
)

data class TambahKategoriResponseErrorData(
    val kategori: List<String>
)
