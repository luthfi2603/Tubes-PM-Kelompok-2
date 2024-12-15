package com.pmkomc22kelompok2.bookjas.ui.user.register.ui.login

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.navigation.Navigation
import com.pmkomc22kelompok2.bookjas.R
import com.pmkomc22kelompok2.bookjas.api.ApiClient
import com.pmkomc22kelompok2.bookjas.databinding.FragmentRegisterBinding
import com.pmkomc22kelompok2.bookjas.ui.user.register.data.model.UserRegisterRequest
import com.pmkomc22kelompok2.bookjas.ui.user.register.data.model.UserResponseRegister
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val namaEditText = binding.nama
        val usernameEditText = binding.username
        val passwordEditText = binding.password
        val confirmPasswordEditText = binding.konfirmasiPassword
        val registerButton = binding.register
        val loadingProgressBar = binding.loading

        // Listener untuk tombol register
        registerButton.setOnClickListener {
            val nama = namaEditText.text.toString()
            val email = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()
            val confirmPassword = confirmPasswordEditText.text.toString()

            // Validasi input
            if (nama.isBlank() || email.isBlank() || password.isBlank() || confirmPassword.isBlank()) {
                Toast.makeText(context, "Harap isi semua field", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password != confirmPassword) {
                    Toast.makeText(context, "Konfirmasi password tidak sesuai", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
            }

            loadingProgressBar.visibility = View.VISIBLE

            // Buat data permintaan untuk API
            val request = UserRegisterRequest(
                nama = nama,
                email = email,
                password = password,
                password_confirmation = confirmPassword
            )

            // Panggil API melalui ApiClient
            ApiClient.apiService.registerUser(request)
                .enqueue(object : Callback<UserResponseRegister> {
                    override fun onResponse(
                        call: Call<UserResponseRegister>,
                        response: Response<UserResponseRegister>
                    ) {
                        loadingProgressBar.visibility = View.GONE
                        if (response.isSuccessful) {
                            Toast.makeText(
                                context,
                                "Registrasi berhasil, selamat datang ${response.body()?.nama}!",
                                Toast.LENGTH_SHORT
                            ).show()
                            Navigation.findNavController(view)
                                .navigate(R.id.action_navigation_register_to_navigation_start)
                        } else {
                            Toast.makeText(
                                context,
                                "Registrasi gagal: ${response.message()}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    override fun onFailure(call: Call<UserResponseRegister>, t: Throwable) {
                        loadingProgressBar.visibility = View.GONE
                        Toast.makeText(context, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                    }
                })
        }

        // Listener untuk teks masuk
        binding.textMasuk.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_navigation_register_to_navigation_login)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
