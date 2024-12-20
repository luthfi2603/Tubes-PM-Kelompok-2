package com.pmkomc22kelompok2.bookjas.ui.admin.kelolakategori

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pmkomc22kelompok2.bookjas.databinding.ItemKategoriBinding

class ListKategoriAdapter(
    private val list: ArrayList<Kategori>,
    private val onEditClick: (Kategori) -> Unit,
    private val onDeleteClick: (Kategori) -> Unit
) : RecyclerView.Adapter<ListKategoriAdapter.ListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(ItemKategoriBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val item = list[position]
        with(holder.binding) {
            tvKategori.text = item.kategori

            btnEdit.setOnClickListener {
                onEditClick(item)
            }

            btnDelete.setOnClickListener {
                onDeleteClick(item)
            }
        }
    }

    inner class ListViewHolder(val binding: ItemKategoriBinding) : RecyclerView.ViewHolder(binding.root)
}