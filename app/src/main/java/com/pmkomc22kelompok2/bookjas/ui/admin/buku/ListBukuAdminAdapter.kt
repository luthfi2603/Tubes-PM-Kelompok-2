package com.pmkomc22kelompok2.bookjas.ui.admin.buku

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pmkomc22kelompok2.bookjas.databinding.ItemBukuAdminBinding
import java.util.Locale

class ListBukuAdminAdapter(private val list: ArrayList<BukuAdmin>) : RecyclerView.Adapter<ListBukuAdminAdapter.ListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(ItemBukuAdminBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (foto, judulBuku, author, deskripsi, jumlahBuku, tahunTerbit, penerbit) = list[position]
        holder.binding.ivFotoBuku.setImageResource(foto)
        holder.binding.tvJudulBuku.text = judulBuku
        holder.binding.tvAuthor.text = author
        holder.binding.tvDeskripsi.text = deskripsi
        holder.binding.tvJumlahBuku.text = String.format(Locale.getDefault(), "%d unit", jumlahBuku)
        holder.binding.tvTahunTerbit.text = tahunTerbit
        holder.binding.tvPenerbit.text = penerbit
    }

    inner class ListViewHolder(val binding: ItemBukuAdminBinding) : RecyclerView.ViewHolder(binding.root)
}