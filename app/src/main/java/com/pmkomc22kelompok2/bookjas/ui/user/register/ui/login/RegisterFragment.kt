package com.pmkomc22kelompok2.bookjas.ui.user.register.ui.login

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
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.navigation.Navigation
import com.pmkomc22kelompok2.bookjas.R
import com.pmkomc22kelompok2.bookjas.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {

    private lateinit var loginViewModel: com.pmkomc22kelompok2.bookjas.ui.user.register.ui.login.LoginViewModel
    private var _binding: FragmentRegisterBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
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
        loginViewModel = ViewModelProvider(this,
            com.pmkomc22kelompok2.bookjas.ui.user.register.ui.login.LoginViewModelFactory()
        )
            .get(com.pmkomc22kelompok2.bookjas.ui.user.register.ui.login.LoginViewModel::class.java)

        val usernameEditText = binding.etEmail
        val passwordEditText = binding.etPassword
        val registerButton = binding.register
        val loadingProgressBar = binding.loading

        loginViewModel.loginFormState.observe(viewLifecycleOwner,
            Observer { loginFormState ->
                if (loginFormState == null) {
                    return@Observer
                }
                registerButton.isEnabled = loginFormState.isDataValid
                loginFormState.usernameError?.let {
                    usernameEditText.error = getString(it)
                }
                loginFormState.passwordError?.let {
                    passwordEditText.error = getString(it)
                }
            })

        loginViewModel.loginResult.observe(viewLifecycleOwner,
            Observer { loginResult ->
                loginResult ?: return@Observer
                loadingProgressBar.visibility = View.GONE
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
                    usernameEditText.text.toString(),
                    passwordEditText.text.toString()
                )
            }
        }
        usernameEditText.addTextChangedListener(afterTextChangedListener)
        passwordEditText.addTextChangedListener(afterTextChangedListener)
        passwordEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                loginViewModel.login(
                    usernameEditText.text.toString(),
                    passwordEditText.text.toString()
                )
            }
            false
        }

        registerButton.setOnClickListener {
            /*loadingProgressBar.visibility = View.VISIBLE
            loginViewModel.login(
                usernameEditText.text.toString(),
                passwordEditText.text.toString()
            )*/
            Navigation.findNavController(view).apply {
                navigate(R.id.action_navigation_register_to_navigation_start)
            }
        }

        binding.textMasuk.setOnClickListener {
            Navigation.findNavController(view).apply {
                navigate(R.id.action_navigation_register_to_navigation_login)
            }
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

    private fun updateUiWithUser(model: com.pmkomc22kelompok2.bookjas.ui.user.register.ui.login.LoggedInUserView) {
        val welcome = getString(R.string.welcome) + model.displayName
        // TODO : initiate successful logged in experience
        val appContext = context?.applicationContext ?: return
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