package com.pmkomc22kelompok2.bookjas.ui.admin.tambahpeminjaman

data class TambahPeminjamanResponseError(
    val errors: TambahPeminjamanResponseErrorData
)

data class TambahPeminjamanResponseErrorData(
    val email: List<String>?,
    val isbn: List<String>?,
    val message: List<String>?,
)
