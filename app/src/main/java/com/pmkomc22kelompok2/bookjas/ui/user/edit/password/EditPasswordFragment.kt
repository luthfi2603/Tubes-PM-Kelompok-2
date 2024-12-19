package com.pmkomc22kelompok2.bookjas.ui.user.edit.password

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.pmkomc22kelompok2.bookjas.R
import com.pmkomc22kelompok2.bookjas.api.ApiClient
import com.pmkomc22kelompok2.bookjas.data.OkResponse
import com.pmkomc22kelompok2.bookjas.databinding.FragmentEditPasswordBinding
import com.pmkomc22kelompok2.bookjas.ui.login.data.LoginRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditPasswordFragment : Fragment() {
    private lateinit var binding: FragmentEditPasswordBinding
    private lateinit var editPasswordViewModel: EditPasswordViewModel

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
        editPasswordViewModel = ViewModelProvider(this).get(EditPasswordViewModel::class.java)

        val etPasswordSaatIni = binding.etPasswordSaatIni
        val etPasswordBaru = binding.etPasswordBaru
        val etKonfirmasiPasswordBaru = binding.etKonfirmasiPasswordBaru
        val btnEditPassword = binding.btnEditPassword
        val loadingProgressBar = binding.loading

        editPasswordViewModel.editPasswordFormState.observe(viewLifecycleOwner, Observer { editPasswordFormState ->
            if (editPasswordFormState == null) {
                return@Observer
            }
            btnEditPassword.isEnabled = editPasswordFormState.isDataValid
            editPasswordFormState.passwordSaatIniError?.let {
                etPasswordSaatIni.error = getString(it)
            }
            editPasswordFormState.passwordBaruError?.let {
                etPasswordBaru.error = getString(it)
            }
            editPasswordFormState.konfirmasiPasswordBaruError?.let {
                etKonfirmasiPasswordBaru.error = getString(it)
            }
        })

        val afterTextChangedListener = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // ignore
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                // ignore
            }

            override fun afterTextChanged(s: Editable) {
                editPasswordViewModel.registerDataChanged(
                    etPasswordSaatIni.text.toString(),
                    etPasswordBaru.text.toString(),
                    etKonfirmasiPasswordBaru.text.toString(),
                )
            }
        }

        etPasswordSaatIni.addTextChangedListener(afterTextChangedListener)
        etPasswordBaru.addTextChangedListener(afterTextChangedListener)
        etKonfirmasiPasswordBaru.addTextChangedListener(afterTextChangedListener)

        // Tombol submit di tekan
        btnEditPassword.setOnClickListener {
            val passwordSaatIni = etPasswordSaatIni.text.toString()
            val passwordBaru = etPasswordBaru.text.toString()
            val konfirmasiPasswordBaru = etKonfirmasiPasswordBaru.text.toString()

            loadingProgressBar.visibility = View.VISIBLE
            binding.vOverlay.visibility = View.VISIBLE

            // Buat data permintaan untuk API
            val request = EditPasswordRequest(
                current_password = passwordSaatIni,
                password = passwordBaru,
                password_confirmation = konfirmasiPasswordBaru,
            )

            // Panggil API melalui ApiClient
            ApiClient.apiService.editPassword(request, LoginRepository.UserManager.user?.token).enqueue(object : Callback<OkResponse> {
                override fun onResponse(
                    call: Call<OkResponse>,
                    response: Response<OkResponse>
                ) {
                    loadingProgressBar.visibility = View.GONE
                    binding.vOverlay.visibility = View.GONE
                    if (response.isSuccessful) {
                        println(response)
                        Toast.makeText(
                            context,
                            response.body()?.message,
                            Toast.LENGTH_SHORT
                        ).show()
                        Navigation.findNavController(view).navigate(R.id.action_editPasswordFragment_to_navigation_settings)
                    } else {
                        val errorBody = response.errorBody()?.string()
                        val errorMessage = errorBody?.let {
                            try {
                                val errorResponse = Gson().fromJson(it, EditPasswordResponseError::class.java)
                                errorResponse.errors ?: "Unknown error"
                            } catch (e: Exception) {
                                Log.e("POST", "Failed to parse error message", e)
                                "Failed to parse error message"
                            }
                        } ?: "Unknown error occurred"
                        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<OkResponse>, t: Throwable) {
                    loadingProgressBar.visibility = View.GONE
                    binding.vOverlay.visibility = View.GONE
                    Toast.makeText(context, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }

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