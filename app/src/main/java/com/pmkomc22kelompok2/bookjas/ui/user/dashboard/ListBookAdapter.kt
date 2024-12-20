package com.pmkomc22kelompok2.bookjas.ui.user.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pmkomc22kelompok2.bookjas.api.ApiClient.UserManager.BASE_URL
import com.pmkomc22kelompok2.bookjas.databinding.ItemRowBookBinding
import com.pmkomc22kelompok2.bookjas.ui.admin.buku.BukuAdminResponseData
import java.util.Locale

class ListBookAdapter(
    private val listBook: ArrayList<BukuAdminResponseData>,
    private val onItemClick: (BukuAdminResponseData) -> Unit
) : RecyclerView.Adapter<ListBookAdapter.ListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(ItemRowBookBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = listBook.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val item = listBook[position]
        with(holder.binding) {
            Glide.with(holder.itemView.context)
                .load(BASE_URL + "/storage/" + item.sampul) // URL Gambar
                .into(ivFotoBuku) // imageView mana yang akan diterapkan
            tvJumlahBuku.text = String.format(Locale.getDefault(), "%d unit", item.jumlah_tersedia)
            tvJudulBuku.text = item.judul
            tvAuthor.text = item.penulis

            root.setOnClickListener {
                onItemClick(item)
            }
        }
    }

    inner class ListViewHolder(val binding: ItemRowBookBinding) : RecyclerView.ViewHolder(binding.root)
}