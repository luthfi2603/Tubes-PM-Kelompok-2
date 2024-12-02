package com.pmkomc22kelompok2.bookjas.ui.admin.tambahbuku

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pmkomc22kelompok2.bookjas.databinding.FragmentTambahBukuBinding

class TambahBukuFragment : Fragment() {
    private lateinit var binding: FragmentTambahBukuBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTambahBukuBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}