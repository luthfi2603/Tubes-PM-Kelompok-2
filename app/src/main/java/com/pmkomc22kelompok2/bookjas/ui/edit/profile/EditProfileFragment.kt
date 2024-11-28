package com.pmkomc22kelompok2.bookjas.ui.edit.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.pmkomc22kelompok2.bookjas.R
import com.pmkomc22kelompok2.bookjas.databinding.FragmentEditProfileBinding

class EditProfileFragment : Fragment() {
    private lateinit var binding: FragmentEditProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditProfileBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fotoProfile.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_editProfileFragment_to_editFotoProfileFragment)
        }

        binding.editFotoProfile.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_editProfileFragment_to_editFotoProfileFragment)
        }
    }
}