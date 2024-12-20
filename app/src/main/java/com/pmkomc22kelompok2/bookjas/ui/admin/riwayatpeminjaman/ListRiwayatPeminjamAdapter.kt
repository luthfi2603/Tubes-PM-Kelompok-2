package com.pmkomc22kelompok2.bookjas.ui.admin.riwayatpeminjaman

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pmkomc22kelompok2.bookjas.api.ApiClient.UserManager.BASE_URL
import com.pmkomc22kelompok2.bookjas.databinding.ItemRiwayatPeminjamBinding
import com.pmkomc22kelompok2.bookjas.ui.user.riwayatpeminjaman.RiwayatPeminjamanResponseData

class ListRiwayatPeminjamAdapter(
    private val listPeminjam: ArrayList<RiwayatPeminjamanResponseData>,
    private val onButtonClick: (RiwayatPeminjamanResponseData) -> Unit
) : RecyclerView.Adapter<ListRiwayatPeminjamAdapter.ListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(ItemRiwayatPeminjamBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = listPeminjam.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val item = listPeminjam[position]
        with(holder.binding) {
            Glide.with(holder.itemView.context)
                .load(BASE_URL + "/storage/" + item.sampul) // URL Gambar
                .into(ivFotoBuku) // imageView mana yang akan diterapkan
            tvJudulBuku.text = item.judul
            tvAuthor.text = item.penulis
            tvTanggalPeminjaman.text = item.tanggal_peminjaman
            tvTanggalPengembalian.text = item.tenggat
            tvTenggatHitungMundur.text = item.hari_tersisa
            tvPeminjam.text = item.peminjam

            if (item.status == "dikembalikan") {
                btnUbahStatusPeminjaman.visibility = View.GONE
                tvStatusDipinjam.visibility = View.INVISIBLE
                tvStatusDikembalikan.visibility = View.VISIBLE

                ivTanggalDikembalikan.visibility = View.VISIBLE
                tvTanggalDikembalikan.visibility = View.VISIBLE
                tvTanggalDikembalikan.text = item.tanggal_pengembalian

                ivIcClock.visibility = View.INVISIBLE
                tvTanggalPengembalian.visibility = View.INVISIBLE
                tvTenggatHitungMundur.visibility = View.INVISIBLE
            }

            btnUbahStatusPeminjaman.setOnClickListener {
                onButtonClick(item)
            }
        }
    }

    inner class ListViewHolder(val binding: ItemRiwayatPeminjamBinding) : RecyclerView.ViewHolder(binding.root)
}