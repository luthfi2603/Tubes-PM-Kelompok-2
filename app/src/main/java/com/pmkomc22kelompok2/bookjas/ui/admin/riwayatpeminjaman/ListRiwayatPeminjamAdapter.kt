package com.pmkomc22kelompok2.bookjas.ui.admin.riwayatpeminjaman

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pmkomc22kelompok2.bookjas.databinding.ItemRiwayatPeminjamBinding

class ListRiwayatPeminjamAdapter(private val listPeminjam: ArrayList<RiwayatPeminjam>) : RecyclerView.Adapter<ListRiwayatPeminjamAdapter.ListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(ItemRiwayatPeminjamBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = listPeminjam.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (foto, judulBuku, author, peminjam, tanggalPeminjaman, tanggalPengembalian) = listPeminjam[position]
        holder.binding.ivFotoBuku.setImageResource(foto)
        holder.binding.tvJudulBuku.text = judulBuku
        holder.binding.tvAuthor.text = author
        holder.binding.tvPeminjam.text = peminjam
        holder.binding.tvTanggalPeminjaman.text = tanggalPeminjaman
        holder.binding.tvTanggalPengembalian.text = tanggalPengembalian
    }

    inner class ListViewHolder(val binding: ItemRiwayatPeminjamBinding) : RecyclerView.ViewHolder(binding.root)
}