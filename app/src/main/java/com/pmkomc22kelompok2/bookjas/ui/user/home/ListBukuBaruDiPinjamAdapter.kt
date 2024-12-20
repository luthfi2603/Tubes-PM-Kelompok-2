package com.pmkomc22kelompok2.bookjas.ui.user.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pmkomc22kelompok2.bookjas.api.ApiClient.UserManager.BASE_URL
import com.pmkomc22kelompok2.bookjas.databinding.ItemBukuBaruDipinjamBinding
import com.pmkomc22kelompok2.bookjas.ui.user.riwayatpeminjaman.RiwayatPeminjamanResponseData

class ListBukuBaruDiPinjamAdapter(
    private val list: ArrayList<RiwayatPeminjamanResponseData>/*,
    private val onButtonClick: (RiwayatPeminjamanResponseData) -> Unit*/
) : RecyclerView.Adapter<ListBukuBaruDiPinjamAdapter.ListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(ItemBukuBaruDipinjamBinding.inflate(LayoutInflater.from(parent.context), parent, false))
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
            tvTenggat.text = item.tenggat

            /*root.setOnClickListener {
                onButtonClick(item)
            }*/
        }
    }

    inner class ListViewHolder(val binding: ItemBukuBaruDipinjamBinding) : RecyclerView.ViewHolder(binding.root)
}