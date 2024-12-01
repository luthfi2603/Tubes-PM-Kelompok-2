package com.pmkomc22kelompok2.bookjas.ui.admin.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.pmkomc22kelompok2.bookjas.R
import com.pmkomc22kelompok2.bookjas.databinding.FragmentDashboardAdminBinding

class DashboardAdminFragment : Fragment() {
    private lateinit var binding: FragmentDashboardAdminBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDashboardAdminBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnToRiwayatPeminjaman.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_dashboardAdminFragment_to_riwayatPeminjamanAdminFragment)
        }
    }
}