package com.pmkomc22kelompok2.bookjas.ui.user.settings

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.pmkomc22kelompok2.bookjas.MainActivity
import com.pmkomc22kelompok2.bookjas.R
import com.pmkomc22kelompok2.bookjas.UserActivity
import com.pmkomc22kelompok2.bookjas.api.ApiClient
import com.pmkomc22kelompok2.bookjas.databinding.FragmentSettingsBinding
import com.pmkomc22kelompok2.bookjas.ui.login.data.LoginDataSource
import com.pmkomc22kelompok2.bookjas.ui.login.data.LoginRepository
import com.pmkomc22kelompok2.bookjas.ui.login.data.LoginRepository.UserManager.user
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SettingsFragment : Fragment() {
    private lateinit var binding: FragmentSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnUbahProfile.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_navigation_settings_to_editProfileFragment)
        }

        binding.tvGantiPassword.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_navigation_settings_to_editPasswordFragment)
        }

        binding.logout.setOnClickListener {
            binding.loading.visibility = View.VISIBLE
            binding.vOverlay.visibility = View.VISIBLE

            // Panggil API melalui ApiClient
            ApiClient.apiService.logout(user?.token).enqueue(object : Callback<Void> {
                override fun onResponse(
                    call: Call<Void>,
                    response: Response<Void>
                ) {
                    if (response.isSuccessful) {
                        // Hapus data pengguna dari SharedPreferences jika ada
                        val sharedPref = requireContext().getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
                        with(sharedPref.edit()) {
                            clear() // Menghapus semua data yang disimpan
                            apply()
                        }
                        val logout = LoginRepository(LoginDataSource())
                        logout.logout()

                        val logoutIntent = Intent(context, MainActivity::class.java).apply {
                            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        }
                        startActivity(logoutIntent)

                        Toast.makeText(
                            context,
                            "Logout berhasil, Selamat Tinggal",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            context,
                            "Logout gagal: ${response.message()}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    binding.loading.visibility = View.GONE
                    binding.vOverlay.visibility = View.GONE
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    binding.loading.visibility = View.GONE
                    binding.vOverlay.visibility = View.GONE
                    Toast.makeText(context, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}