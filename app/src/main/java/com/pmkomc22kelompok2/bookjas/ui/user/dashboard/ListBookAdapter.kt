package com.pmkomc22kelompok2.bookjas.ui.user.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pmkomc22kelompok2.bookjas.databinding.ItemRowBookBinding
import java.util.Locale

class ListBookAdapter(private val listBook: ArrayList<Book>) : RecyclerView.Adapter<ListBookAdapter.ListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(ItemRowBookBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = listBook.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (foto, jumlahBuku, judulBuku, author) = listBook[position]
        holder.binding.ivFotoBuku.setImageResource(foto)
        holder.binding.tvJumlahBuku.text = String.format(Locale.getDefault(), "%d unit", jumlahBuku)
        holder.binding.tvJudulBuku.text = judulBuku
        holder.binding.tvAuthor.text = author
    }

    inner class ListViewHolder(val binding: ItemRowBookBinding) : RecyclerView.ViewHolder(binding.root)
}