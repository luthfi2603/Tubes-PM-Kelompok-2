package com.pmkomc22kelompok2.bookjas.ui.user.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pmkomc22kelompok2.bookjas.databinding.ItemKategoriBukuBinding

class ListKategoriAdapter(private val list: ArrayList<KategoriData>) : RecyclerView.Adapter<ListKategoriAdapter.ListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(ItemKategoriBukuBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val item = list[position]
        holder.binding.tvKategoriBuku.text = item.kategori
    }

    inner class ListViewHolder(val binding: ItemKategoriBukuBinding) : RecyclerView.ViewHolder(binding.root)
}