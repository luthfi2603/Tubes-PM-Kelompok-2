package com.pmkomc22kelompok2.bookjas.ui.user.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pmkomc22kelompok2.bookjas.databinding.ItemBukuBaruDipinjamBinding

class ListBukuBaruDiPinjamAdapter(private val list: ArrayList<BukuBaruDiPinjam>) : RecyclerView.Adapter<ListBukuBaruDiPinjamAdapter.ListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(ItemBukuBaruDipinjamBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (foto, judulBuku, author) = list[position]
        holder.binding.ivFotoBuku.setImageResource(foto)
        holder.binding.tvJudulBuku.text = judulBuku
        holder.binding.tvAuthor.text = author
    }

    inner class ListViewHolder(val binding: ItemBukuBaruDipinjamBinding) : RecyclerView.ViewHolder(binding.root)
}