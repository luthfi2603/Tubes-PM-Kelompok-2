package com.pmkomc22kelompok2.bookjas.ui.user.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pmkomc22kelompok2.bookjas.databinding.ItemRekomendasiBukuBinding

class ListRekomendasiBukuAdapter(private val list: ArrayList<RekomendasiBuku>) : RecyclerView.Adapter<ListRekomendasiBukuAdapter.ListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(ItemRekomendasiBukuBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (foto, judulBuku, author) = list[position]
        holder.binding.ivFotoBuku.setImageResource(foto)
        holder.binding.tvJudulBuku.text = judulBuku
        holder.binding.tvAuthor.text = author
    }

    inner class ListViewHolder(val binding: ItemRekomendasiBukuBinding) : RecyclerView.ViewHolder(binding.root)
}