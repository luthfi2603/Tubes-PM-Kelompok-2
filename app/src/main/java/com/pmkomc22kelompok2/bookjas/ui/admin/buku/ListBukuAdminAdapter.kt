package com.pmkomc22kelompok2.bookjas.ui.admin.buku

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pmkomc22kelompok2.bookjas.databinding.ItemBukuAdminBinding
import java.util.Locale

class ListBukuAdminAdapter(
    private val list: ArrayList<BukuAdmin>,
    private val onButtonClick: (BukuAdmin) -> Unit
) : RecyclerView.Adapter<ListBukuAdminAdapter.ListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(ItemBukuAdminBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (foto, judulBuku, author, deskripsi, jumlahBuku, tahunTerbit, penerbit) = list[position]
        with(holder.binding) {
            ivFotoBuku.setImageResource(foto)
            tvJudulBuku.text = judulBuku
            tvAuthor.text = author
            tvDeskripsi.text = deskripsi
            tvJumlahBuku.text = String.format(Locale.getDefault(), "%d unit", jumlahBuku)
            tvTahunTerbit.text = tahunTerbit
            tvPenerbit.text = penerbit

            btnEdit.setOnClickListener {
                onButtonClick(list[position])
            }
        }
    }

    inner class ListViewHolder(val binding: ItemBukuAdminBinding) : RecyclerView.ViewHolder(binding.root)
}