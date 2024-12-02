package com.pmkomc22kelompok2.bookjas.ui.admin.editbuku

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pmkomc22kelompok2.bookjas.databinding.FragmentEditBukuBinding

class EditBukuFragment : Fragment() {
    private lateinit var binding: FragmentEditBukuBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditBukuBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}