package com.pmkomc22kelompok2.bookjas.ui.user.edit.profile

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.google.gson.Gson
import com.pmkomc22kelompok2.bookjas.R
import com.pmkomc22kelompok2.bookjas.api.ApiClient
import com.pmkomc22kelompok2.bookjas.data.OkResponse
import com.pmkomc22kelompok2.bookjas.databinding.FragmentEditProfileBinding
import com.pmkomc22kelompok2.bookjas.ui.login.data.LoginRepository.UserManager.user
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditProfileFragment : Fragment() {
    private lateinit var binding: FragmentEditProfileBinding
    private val editProfileViewModel: EditProfileViewModel by viewModels()

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

        val etNamaLengkap = binding.etNamaLengkap
        val etEmail = binding.etEmail
        val btnUbahProfile = binding.btnUbahProfile

        etNamaLengkap.setText(user?.nama.toString())
        etEmail.setText(user?.email.toString())

        editProfileViewModel.editProfileFormState.observe(viewLifecycleOwner, Observer { editProfileFormState ->
            if (editProfileFormState == null) {
                return@Observer
            }

            btnUbahProfile.isEnabled = editProfileFormState.isDataValid

            editProfileFormState.namaError?.let {
                etNamaLengkap.error = getString(it)
            }
            editProfileFormState.emailError?.let {
                etEmail.error = getString(it)
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
                editProfileViewModel.registerDataChanged(
                    etNamaLengkap.text.toString(),
                    etEmail.text.toString(),
                )
            }
        }

        etNamaLengkap.addTextChangedListener(afterTextChangedListener)
        etEmail.addTextChangedListener(afterTextChangedListener)

        // Tombol submit di tekan
        btnUbahProfile.setOnClickListener {
            val namaLengkap = etNamaLengkap.text.toString()
            val email = etEmail.text.toString()

            binding.loading.visibility = View.VISIBLE
            binding.vOverlay.visibility = View.VISIBLE

            // Buat data permintaan untuk API
            val request = EditProfileRequest(
                nama = namaLengkap,
                email = email,
            )

            // Panggil API melalui ApiClient
            ApiClient.apiService.editProfile(request, user?.token).enqueue(object : Callback<OkResponse> {
                override fun onResponse(
                    call: Call<OkResponse>,
                    response: Response<OkResponse>
                ) {
                    binding.loading.visibility = View.GONE
                    binding.vOverlay.visibility = View.GONE
                    if (response.isSuccessful) {
                        val sharedPref = context?.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

                        with(sharedPref?.edit()) {
                            this?.putString("user_nama", namaLengkap)
                            this?.putString("user_email", email)
                            this?.apply()
                        }

                        user?.nama = namaLengkap
                        user?.email = email

                        Navigation.findNavController(view).navigate(R.id.action_editProfileFragment_to_navigation_settings)

                        Toast.makeText(
                            context,
                            response.body()?.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        val errorBody = response.errorBody()?.string()
                        val errorMessage = errorBody?.let {
                            try {
                                val errorResponse = Gson().fromJson(it, EditProfileErrorResponse::class.java)

                                // Periksa apakah field tertentu memiliki error
                                val namaError = errorResponse.errors.nama
                                val emailError = errorResponse.errors.email

                                // Bangun pesan error berdasarkan response
                                val errorMessages = mutableListOf<String>()
                                if (namaError.isNotEmpty()) errorMessages.add("Nama: $namaError")
                                if (emailError.isNotEmpty()) errorMessages.add("Email: $emailError")

                                // Gabungkan pesan error
                                errorMessages.joinToString("\n")
                            } catch (e: Exception) {
                                Log.e("EDIT_PROFILE", "Failed to parse error message", e)
                                "Failed to parse error message"
                            }
                        } ?: "Unknown error occurred"
                        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<OkResponse>, t: Throwable) {
                    binding.loading.visibility = View.GONE
                    binding.vOverlay.visibility = View.GONE
                    Toast.makeText(context, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }

        /*binding.fotoProfile.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_editProfileFragment_to_editFotoProfileFragment)
        }

        binding.editFotoProfile.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_editProfileFragment_to_editFotoProfileFragment)
        }*/
    }
}