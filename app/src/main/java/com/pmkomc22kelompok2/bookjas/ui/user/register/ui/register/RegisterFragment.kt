package com.pmkomc22kelompok2.bookjas.ui.user.register.ui.register

import androidx.lifecycle.Observer
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.pmkomc22kelompok2.bookjas.R
import com.pmkomc22kelompok2.bookjas.api.ApiClient
import com.pmkomc22kelompok2.bookjas.databinding.FragmentRegisterBinding
import com.pmkomc22kelompok2.bookjas.ui.user.register.data.model.UserRegisterRequest
import com.pmkomc22kelompok2.bookjas.ui.user.register.data.model.UserRegisterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterFragment : Fragment() {
    private lateinit var registerViewModel: RegisterViewModel
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerViewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)

        val namaEditText = binding.etNama
        val emailEditText = binding.etEmail
        val passwordEditText = binding.etPassword
        val konfirmasiPasswordEditText = binding.etKonfirmasiPassword
        val registerButton = binding.register
        val loadingProgressBar = binding.loading

        registerViewModel.registerFormState.observe(viewLifecycleOwner, Observer { registerFormState ->
            if (registerFormState == null) {
                return@Observer
            }
            registerButton.isEnabled = registerFormState.isDataValid
            registerFormState.namaError?.let {
                namaEditText.error = getString(it)
            }
            registerFormState.emailError?.let {
                emailEditText.error = getString(it)
            }
            registerFormState.passwordError?.let {
                passwordEditText.error = getString(it)
            }
            registerFormState.konfirmasiPasswordError?.let {
                konfirmasiPasswordEditText.error = getString(it)
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
                registerViewModel.registerDataChanged(
                    namaEditText.text.toString(),
                    emailEditText.text.toString(),
                    passwordEditText.text.toString(),
                    konfirmasiPasswordEditText.text.toString()
                )
            }
        }

        namaEditText.addTextChangedListener(afterTextChangedListener)
        emailEditText.addTextChangedListener(afterTextChangedListener)
        passwordEditText.addTextChangedListener(afterTextChangedListener)
        konfirmasiPasswordEditText.addTextChangedListener(afterTextChangedListener)

        // Listener untuk tombol register
        registerButton.setOnClickListener {
            val nama = namaEditText.text.toString()
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            val confirmPassword = konfirmasiPasswordEditText.text.toString()

            // Validasi input
            /*if (nama.isBlank() || email.isBlank() || password.isBlank() || confirmPassword.isBlank()) {
                Toast.makeText(context, "Harap isi semua field", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }*/

            if (password != confirmPassword) {
                Toast.makeText(context, "Konfirmasi password tidak sesuai", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            loadingProgressBar.visibility = View.VISIBLE
            binding.vOverlay.visibility = View.VISIBLE

            // Buat data permintaan untuk API
            val request = UserRegisterRequest(
                nama = nama,
                email = email,
                password = password,
                password_confirmation = confirmPassword
            )

            // Panggil API melalui ApiClient
            ApiClient.apiService.registerUser(request)
                .enqueue(object : Callback<UserRegisterResponse> {
                    override fun onResponse(
                        call: Call<UserRegisterResponse>,
                        response: Response<UserRegisterResponse>
                    ) {
                        loadingProgressBar.visibility = View.GONE
                        binding.vOverlay.visibility = View.GONE
                        if (response.isSuccessful) {
                            Toast.makeText(
                                context,
                                "Registrasi berhasil, selamat datang ${response.body()?.data?.nama}!",
                                Toast.LENGTH_SHORT
                            ).show()
                            Navigation.findNavController(view).navigate(R.id.action_navigation_register_to_navigation_start)
                        } else {
                            Toast.makeText(
                                context,
                                "Registrasi gagal: ${response.message()}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    override fun onFailure(call: Call<UserRegisterResponse>, t: Throwable) {
                        loadingProgressBar.visibility = View.GONE
                        binding.vOverlay.visibility = View.GONE
                        Toast.makeText(context, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                    }
                })
        }

        // Listener untuk teks masuk
        binding.textMasuk.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_navigation_register_to_navigation_login)
        }

        // logika untuk toggle show dan hide password
        binding.ivIcShowPassword.setOnClickListener {
            binding.etPassword.inputType = android.text.InputType.TYPE_CLASS_TEXT or android.text.InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            binding.ivIcShowPassword.visibility = View.GONE
            binding.ivIcHidePassword.visibility = View.VISIBLE
            binding.etPassword.setSelection(binding.etPassword.text?.length ?: 0)
        }

        binding.ivIcHidePassword.setOnClickListener {
            binding.etPassword.inputType = android.text.InputType.TYPE_CLASS_TEXT or android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD
            binding.ivIcShowPassword.visibility = View.VISIBLE
            binding.ivIcHidePassword.visibility = View.GONE
            binding.etPassword.setSelection(binding.etPassword.text?.length ?: 0)
        }

        binding.ivIcShowKonfirmasiPassword.setOnClickListener {
            binding.etKonfirmasiPassword.inputType = android.text.InputType.TYPE_CLASS_TEXT or android.text.InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            binding.ivIcShowKonfirmasiPassword.visibility = View.GONE
            binding.ivIcHideKonfirmasiPassword.visibility = View.VISIBLE
            binding.etKonfirmasiPassword.setSelection(binding.etKonfirmasiPassword.text?.length ?: 0)
        }

        binding.ivIcHideKonfirmasiPassword.setOnClickListener {
            binding.etKonfirmasiPassword.inputType = android.text.InputType.TYPE_CLASS_TEXT or android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD
            binding.ivIcShowKonfirmasiPassword.visibility = View.VISIBLE
            binding.ivIcHideKonfirmasiPassword.visibility = View.GONE
            binding.etKonfirmasiPassword.setSelection(binding.etKonfirmasiPassword.text?.length ?: 0)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
