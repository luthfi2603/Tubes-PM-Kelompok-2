package com.pmkomc22kelompok2.bookjas.ui.user.riwayatpeminjaman

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pmkomc22kelompok2.bookjas.databinding.ItemRiwayatPeminjamanBinding

class ListRiwayatPeminjamanAdapter(
    private val list: ArrayList<RiwayatPeminjaman>,
) : RecyclerView.Adapter<ListRiwayatPeminjamanAdapter.ListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(ItemRiwayatPeminjamanBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val item = list[position]
        with(holder.binding) {
            ivFotoBuku.setImageResource(item.foto)
            tvJudulBuku.text = item.judulBuku
            tvAuthor.text = item.author
            tvTanggalPeminjaman.text = item.tanggalPeminjaman
            tvTanggalPengembalian.text = item.tanggalPengembalian
        }
    }

    inner class ListViewHolder(val binding: ItemRiwayatPeminjamanBinding) : RecyclerView.ViewHolder(binding.root)
}