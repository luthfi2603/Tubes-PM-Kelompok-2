package com.pmkomc22kelompok2.bookjas.ui.user.riwayatpeminjaman

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pmkomc22kelompok2.bookjas.api.ApiClient.UserManager.BASE_URL
import com.pmkomc22kelompok2.bookjas.databinding.ItemRiwayatPeminjamanBinding

class ListRiwayatPeminjamanAdapter(
    private val list: ArrayList<RiwayatPeminjamanResponseData>,
) : RecyclerView.Adapter<ListRiwayatPeminjamanAdapter.ListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(ItemRiwayatPeminjamanBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val item = list[position]
        with(holder.binding) {
            Glide.with(holder.itemView.context)
                .load(BASE_URL + "/storage/" + item.sampul) // URL Gambar
                .into(ivFotoBuku) // imageView mana yang akan diterapkan
            tvJudulBuku.text = item.judul
            tvAuthor.text = item.penulis
            tvTanggalPeminjaman.text = item.tanggal_peminjaman
            tvTanggalPengembalian.text = item.tenggat
            tvTenggatHitungMundur.text = item.hari_tersisa

            if (item.status == "dikembalikan") {
                tvStatusDipinjam.visibility = View.GONE
                tvStatusDikembalikan.visibility = View.VISIBLE

                ivTanggalDikembalikan.visibility = View.VISIBLE
                tvTanggalDikembalikan.visibility = View.VISIBLE
                tvTanggalDikembalikan.text = item.tanggal_pengembalian

                ivIcClock.visibility = View.GONE
                tvTanggalPengembalian.visibility = View.GONE
                tvTenggatHitungMundur.visibility = View.GONE
            }
        }
    }

    inner class ListViewHolder(val binding: ItemRiwayatPeminjamanBinding) : RecyclerView.ViewHolder(binding.root)
}