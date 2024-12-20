package com.pmkomc22kelompok2.bookjas.ui.login.ui.login

import android.content.Context
import android.content.Intent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.navigation.Navigation
import com.pmkomc22kelompok2.bookjas.AdminActivity
import com.pmkomc22kelompok2.bookjas.R
import com.pmkomc22kelompok2.bookjas.UserActivity
import com.pmkomc22kelompok2.bookjas.databinding.FragmentLoginBinding
import com.pmkomc22kelompok2.bookjas.ui.login.data.model.UserLoginResponseData

class LoginFragment : Fragment() {

    private lateinit var loginViewModel: LoginViewModel
    private var _binding: FragmentLoginBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginViewModel = ViewModelProvider(this, LoginViewModelFactory()).get(LoginViewModel::class.java)

        // Akses SharedPreferences untuk memeriksa apakah ada data yang disimpan
        val sharedPref = requireContext().getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val userEmail = sharedPref.getString("user_email", null)
        val userPassword = sharedPref.getString("user_password", null)

        if (userEmail != null) {
            binding.loading.visibility = View.VISIBLE
            binding.vOverlay.visibility = View.VISIBLE

            loginViewModel.login(userEmail, userPassword!!)
        }

        val emailEditText = binding.etEmail
        val passwordEditText = binding.etPassword
        val loginButton = binding.login
        val loadingProgressBar = binding.loading

        loginViewModel.loginFormState.observe(viewLifecycleOwner, Observer { loginFormState ->
            if (loginFormState == null) {
                return@Observer
            }
            loginButton.isEnabled = loginFormState.isDataValid
            loginFormState.emailError?.let {
                emailEditText.error = getString(it)
            }
            loginFormState.passwordError?.let {
                passwordEditText.error = getString(it)
            }
        })

        loginViewModel.loginResult.observe(viewLifecycleOwner, Observer { loginResult ->
            loginResult ?: return@Observer

            loadingProgressBar.visibility = View.GONE
            binding.vOverlay.visibility = View.GONE

            loginResult.error?.let {
                showLoginFailed(it)
            }

            loginResult.success?.let {
                updateUiWithUser(it)
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
                loginViewModel.loginDataChanged(
                    emailEditText.text.toString(),
                    passwordEditText.text.toString()
                )
            }
        }
        emailEditText.addTextChangedListener(afterTextChangedListener)
        passwordEditText.addTextChangedListener(afterTextChangedListener)
        passwordEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                loadingProgressBar.visibility = View.VISIBLE
                binding.vOverlay.visibility = View.VISIBLE

                loginViewModel.login(
                    emailEditText.text.toString(),
                    passwordEditText.text.toString()
                )
            }
            false
        }

        loginButton.setOnClickListener {
            loadingProgressBar.visibility = View.VISIBLE
            binding.vOverlay.visibility = View.VISIBLE

            loginViewModel.login(
                emailEditText.text.toString(),
                passwordEditText.text.toString()
            )
        }

        // Listener untuk teks daftar
        binding.textDaftar.setOnClickListener {
            Navigation.findNavController(view).apply {
                navigate(R.id.action_loginFragment_to_registerFragment)
            }
        }

        // logika untuk toggle show dan hide password
        binding.ivIcShowPassword.setOnClickListener {
            binding.etPassword.inputType = android.text.InputType.TYPE_CLASS_TEXT or android.text.InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            binding.ivIcShowPassword.visibility = View.GONE
            binding.ivIcHidePassword.visibility = View.VISIBLE
            binding.etPassword.setSelection(binding.etPassword.text?.length ?: 0) // Atur ulang posisi kursor
        }

        binding.ivIcHidePassword.setOnClickListener {
            binding.etPassword.inputType = android.text.InputType.TYPE_CLASS_TEXT or android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD
            binding.ivIcShowPassword.visibility = View.VISIBLE
            binding.ivIcHidePassword.visibility = View.GONE
            binding.etPassword.setSelection(binding.etPassword.text?.length ?: 0) // Atur ulang posisi kursor
        }
    }

    private fun updateUiWithUser(model: UserLoginResponseData) {
        val welcome = getString(R.string.welcome) + model.nama
        val appContext = context?.applicationContext ?: return

        /*if (model.status == "User") {
            val userIntent = Intent(context, UserActivity::class.java)
            startActivity(userIntent)
        } else if (model.status == "Admin") {
            val userIntent = Intent(context, UserActivity::class.java)
            startActivity(userIntent)
        }*/

        // simpan data ke preferences, penyimpanan persistant
        val sharedPref = appContext.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putString("user_id", model.id)
            putString("user_email", model.email)
            putString("user_nama", model.nama)
            putString("user_token", model.token)
            putString("user_status", model.status)
            putString("user_password", model.password)
            apply()
        }

        if (model.status == "User") {
            val userIntent = Intent(context, UserActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            startActivity(userIntent)
        } else if (model.status == "Admin") {
            val adminIntent = Intent(context, AdminActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            startActivity(adminIntent)
        }

        Toast.makeText(appContext, welcome, Toast.LENGTH_LONG).show()
    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        val appContext = context?.applicationContext ?: return
        Toast.makeText(appContext, errorString, Toast.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}