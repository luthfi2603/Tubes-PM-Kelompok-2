package com.pmkomc22kelompok2.bookjas.ui.user.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pmkomc22kelompok2.bookjas.databinding.ItemRowBookBinding
import java.util.Locale

class ListBookAdapter(
    private val listBook: ArrayList<Book>,
    private val onItemClick: (Book) -> Unit
) : RecyclerView.Adapter<ListBookAdapter.ListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(ItemRowBookBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = listBook.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (foto, jumlahBuku, judulBuku, author) = listBook[position]
        with(holder.binding) {
            ivFotoBuku.setImageResource(foto)
            tvJumlahBuku.text = String.format(Locale.getDefault(), "%d unit", jumlahBuku)
            tvJudulBuku.text = judulBuku
            tvAuthor.text = author

            root.setOnClickListener {
                onItemClick(listBook[position])
            }
        }
    }

    inner class ListViewHolder(val binding: ItemRowBookBinding) : RecyclerView.ViewHolder(binding.root)
}