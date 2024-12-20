package com.pmkomc22kelompok2.bookjas.ui.user.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.pmkomc22kelompok2.bookjas.R
import com.pmkomc22kelompok2.bookjas.api.ApiClient
import com.pmkomc22kelompok2.bookjas.databinding.FragmentDashboardBinding
import com.pmkomc22kelompok2.bookjas.ui.admin.buku.BukuAdminResponseData
import com.pmkomc22kelompok2.bookjas.ui.admin.buku.BukuAdminViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardFragment : Fragment() {
    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var list: ArrayList<BukuAdminResponseData>? = ArrayList()
    private val listKategori = ArrayList<KategoriData>()
    private val bukuAdminViewModel: BukuAdminViewModel by viewModels()

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

        binding.loading.visibility = View.VISIBLE
        binding.vOverlay.visibility = View.VISIBLE

        bukuAdminViewModel.isLoading.observe(requireActivity()) { isLoading ->
            if (!isLoading) {
                binding.rvBooks.setHasFixedSize(true)
                bukuAdminViewModel.listBuku.observe(requireActivity()) { listBuku ->
                    list = listBuku
                }
                binding.loading.visibility = View.GONE
                binding.vOverlay.visibility = View.GONE
                showRecyclerList()
            }
        }

        if (!bukuAdminViewModel.isLoading.value!!) {
            binding.rvBooks.setHasFixedSize(true)
            bukuAdminViewModel.listBuku.observe(requireActivity()) { listBuku ->
                list = listBuku
            }
            binding.loading.visibility = View.GONE
            binding.vOverlay.visibility = View.GONE
            showRecyclerList()
        }

        binding.rvKategoriBuku.setHasFixedSize(true)
        fetchKategori()

        showRecyclerList()
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

    private fun showRecyclerList() {
        binding.rvBooks.layoutManager = GridLayoutManager(context, 3)
        val listBukuAdapter = ListBookAdapter(list!!) { item ->
            val bundle = Bundle().apply {
                putParcelable("bukuData", item)
            }
            Navigation.findNavController(binding.root).navigate(R.id.action_navigation_dashboard_to_detailBukuFragment, bundle)
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