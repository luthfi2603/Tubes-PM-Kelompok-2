package com.pmkomc22kelompok2.bookjas.ui.admin.riwayatpeminjaman

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pmkomc22kelompok2.bookjas.databinding.ItemRiwayatPeminjamBinding

class ListRiwayatPeminjamAdapter(
    private val listPeminjam: ArrayList<RiwayatPeminjam>,
    private val onButtonClick: (RiwayatPeminjam) -> Unit
) : RecyclerView.Adapter<ListRiwayatPeminjamAdapter.ListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(ItemRiwayatPeminjamBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = listPeminjam.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val item = listPeminjam[position]
        with(holder.binding) {
            ivFotoBuku.setImageResource(item.foto)
            tvJudulBuku.text = item.judulBuku
            tvAuthor.text = item.author
            tvPeminjam.text = item.peminjam
            tvTanggalPeminjaman.text = item.tanggalPeminjaman
            tvTanggalPengembalian.text = item.tanggalPengembalian

            btnEdit.setOnClickListener {
                onButtonClick(item)
            }
        }
    }

    inner class ListViewHolder(val binding: ItemRiwayatPeminjamBinding) : RecyclerView.ViewHolder(binding.root)
}