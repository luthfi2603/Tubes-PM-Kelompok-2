package com.pmkomc22kelompok2.bookjas.ui.user.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pmkomc22kelompok2.bookjas.api.ApiClient.UserManager.BASE_URL
import com.pmkomc22kelompok2.bookjas.databinding.ItemRekomendasiBukuBinding
import com.pmkomc22kelompok2.bookjas.ui.admin.buku.BukuAdminResponseData
import java.util.Locale

class ListRekomendasiBukuAdapter(
    private val list: ArrayList<BukuAdminResponseData>,
    private val onButtonClick: (BukuAdminResponseData) -> Unit
) : RecyclerView.Adapter<ListRekomendasiBukuAdapter.ListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(ItemRekomendasiBukuBinding.inflate(LayoutInflater.from(parent.context), parent, false))
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

            root.setOnClickListener {
                onButtonClick(item)
            }
        }
    }

    inner class ListViewHolder(val binding: ItemRekomendasiBukuBinding) : RecyclerView.ViewHolder(binding.root)
}