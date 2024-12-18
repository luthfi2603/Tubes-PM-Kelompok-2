package com.pmkomc22kelompok2.bookjas.ui.user.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.pmkomc22kelompok2.bookjas.R
import com.pmkomc22kelompok2.bookjas.api.ApiClient
import com.pmkomc22kelompok2.bookjas.databinding.FragmentDashboardBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardFragment : Fragment() {
    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val list = ArrayList<Book>()
    private val listKategori = ArrayList<KategoriData>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        /*val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)*/

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        /*val textView: TextView = binding.textSelamatDatang
        dashboardViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }*/
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvBooks.setHasFixedSize(true)
        list.addAll(getListBooks())

        binding.rvKategoriBuku.setHasFixedSize(true)
        // listKategori.addAll(getListKategori())
        fetchKategori()

        showRecyclerList()
    }

    private fun getListBooks(): ArrayList<Book> {
        val foto = resources.obtainTypedArray(R.array.foto)
        val jumlahBuku = resources.getIntArray(R.array.jumlah_buku)
        val judulBuku = resources.getStringArray(R.array.judul_buku)
        val author = resources.getStringArray(R.array.author)
        val listBook = ArrayList<Book>()

        try {
            for (i in jumlahBuku.indices) {
                val book = Book(foto.getResourceId(i, -1), jumlahBuku[i], judulBuku[i], author[i])
                listBook.add(book)
            }
        } finally {
            foto.recycle()
        }

        return listBook
    }

    private fun fetchKategori() {
        ApiClient.apiService.getKategori().enqueue(object : Callback<ListKategoriResponse> {
            override fun onResponse(call: Call<ListKategoriResponse>, response: Response<ListKategoriResponse>) {
                if (response.isSuccessful) {
                    val items = response.body()?.data
                    val listItem = ArrayList<KategoriData>()

                    items?.forEach { item ->
                        val data = KategoriData(item.kategori)
                        listItem.add(data)
                    }

                    listKategori.clear() // Bersihkan data lama jika ada
                    listKategori.addAll(listItem)
                    (binding.rvKategoriBuku.adapter as? ListKategoriAdapter)?.notifyDataSetChanged()
                } else {
                    // Tangani kesalahan jika perlu
                }
            }

            override fun onFailure(call: Call<ListKategoriResponse>, t: Throwable) {
                // Tangani kegagalan jaringan
            }
        })
    }

    private fun getListKategori(): ArrayList<KategoriData> {
        val kategori = resources.getStringArray(R.array.kategori)
        val listItem = ArrayList<KategoriData>()

        for (i in kategori.indices) {
            val item = KategoriData(kategori[i])
            listItem.add(item)
        }

        return listItem
    }

    private fun showRecyclerList() {
        binding.rvBooks.layoutManager = GridLayoutManager(context, 3)
        val listBukuAdapter = ListBookAdapter(list) { item ->
            Navigation.findNavController(binding.root).navigate(R.id.action_navigation_dashboard_to_detailBukuFragment)
        }
        binding.rvBooks.adapter = listBukuAdapter

        binding.rvKategoriBuku.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val listKategoriAdapter = ListKategoriAdapter(listKategori)
        binding.rvKategoriBuku.adapter = listKategoriAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}