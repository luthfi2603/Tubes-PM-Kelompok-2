package com.pmkomc22kelompok2.bookjas.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.pmkomc22kelompok2.bookjas.R
import com.pmkomc22kelompok2.bookjas.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {
    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val list = ArrayList<Book>()

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

    private fun showRecyclerList() {
        binding.rvBooks.layoutManager = GridLayoutManager(context, 3)
        val listHeroAdapter = ListBookAdapter(list)
        binding.rvBooks.adapter = listHeroAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}