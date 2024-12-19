package com.pmkomc22kelompok2.bookjas.ui.admin.buku

import android.os.Build.VERSION_CODES.BASE
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pmkomc22kelompok2.bookjas.databinding.ItemBukuAdminBinding
import java.util.Locale

class ListBukuAdminAdapter(
    private val list: ArrayList<BukuAdminResponseData>,
    private val onButtonClick: (BukuAdminResponseData) -> Unit
) : RecyclerView.Adapter<ListBukuAdminAdapter.ListViewHolder>() {
    val BASE_URL = "http://192.168.150.210:8000"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(ItemBukuAdminBinding.inflate(LayoutInflater.from(parent.context), parent, false))
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
            tvDeskripsi.text = item.deskripsi
            tvJumlahBuku.text = String.format(Locale.getDefault(), "%d unit", item.jumlah_tersedia)
            tvTahunTerbit.text = item.tahun_terbit
            tvPenerbit.text = item.penerbit

            btnEdit.setOnClickListener {
                onButtonClick(list[position])
            }
        }
    }

    inner class ListViewHolder(val binding: ItemBukuAdminBinding) : RecyclerView.ViewHolder(binding.root)
}