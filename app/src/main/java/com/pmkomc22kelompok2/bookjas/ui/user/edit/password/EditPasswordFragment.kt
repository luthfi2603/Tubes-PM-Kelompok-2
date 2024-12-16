package com.pmkomc22kelompok2.bookjas.ui.user.edit.password

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.pmkomc22kelompok2.bookjas.R
import com.pmkomc22kelompok2.bookjas.databinding.FragmentEditPasswordBinding

class EditPasswordFragment : Fragment() {
    private lateinit var binding: FragmentEditPasswordBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditPasswordBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // logika untuk toggle show dan hide password
        binding.ivIcShowPasswordSaatIni.setOnClickListener {
            binding.etPasswordSaatIni.inputType = android.text.InputType.TYPE_CLASS_TEXT or android.text.InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            binding.ivIcShowPasswordSaatIni.visibility = View.GONE
            binding.ivIcHidePasswordSaatIni.visibility = View.VISIBLE
            binding.etPasswordSaatIni.setSelection(binding.etPasswordSaatIni.text?.length ?: 0)
        }

        binding.ivIcHidePasswordSaatIni.setOnClickListener {
            binding.etPasswordSaatIni.inputType = android.text.InputType.TYPE_CLASS_TEXT or android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD
            binding.ivIcShowPasswordSaatIni.visibility = View.VISIBLE
            binding.ivIcHidePasswordSaatIni.visibility = View.GONE
            binding.etPasswordSaatIni.setSelection(binding.etPasswordSaatIni.text?.length ?: 0)
        }

        binding.ivIcShowPasswordBaru.setOnClickListener {
            binding.etPasswordBaru.inputType = android.text.InputType.TYPE_CLASS_TEXT or android.text.InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            binding.ivIcShowPasswordBaru.visibility = View.GONE
            binding.ivIcHidePasswordBaru.visibility = View.VISIBLE
            binding.etPasswordBaru.setSelection(binding.etPasswordBaru.text?.length ?: 0)
        }

        binding.ivIcHidePasswordBaru.setOnClickListener {
            binding.etPasswordBaru.inputType = android.text.InputType.TYPE_CLASS_TEXT or android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD
            binding.ivIcShowPasswordBaru.visibility = View.VISIBLE
            binding.ivIcHidePasswordBaru.visibility = View.GONE
            binding.etPasswordBaru.setSelection(binding.etPasswordBaru.text?.length ?: 0)
        }

        binding.ivIcShowKonfirmasiPasswordBaru.setOnClickListener {
            binding.etKonfirmasiPasswordBaru.inputType = android.text.InputType.TYPE_CLASS_TEXT or android.text.InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            binding.ivIcShowKonfirmasiPasswordBaru.visibility = View.GONE
            binding.ivIcHideKonfirmasiPasswordBaru.visibility = View.VISIBLE
            binding.etKonfirmasiPasswordBaru.setSelection(binding.etKonfirmasiPasswordBaru.text?.length ?: 0)
        }

        binding.ivIcHideKonfirmasiPasswordBaru.setOnClickListener {
            binding.etKonfirmasiPasswordBaru.inputType = android.text.InputType.TYPE_CLASS_TEXT or android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD
            binding.ivIcShowKonfirmasiPasswordBaru.visibility = View.VISIBLE
            binding.ivIcHideKonfirmasiPasswordBaru.visibility = View.GONE
            binding.etKonfirmasiPasswordBaru.setSelection(binding.etKonfirmasiPasswordBaru.text?.length ?: 0)
        }
    }
}