package com.ah_developers.samitestproject.ui.login.fragment

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ah_developers.samitestproject.R
import com.ah_developers.samitestproject.databinding.FragmentLoginBinding
import com.ah_developers.samitestproject.model.Login
import com.ah_developers.samitestproject.repository.Repo
import com.ah_developers.samitestproject.viewModel.MainViewModel
import com.ah_developers.samitestproject.viewModel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar


class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var mainViewModel: MainViewModel

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val window = activity?.window
        window?.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        requireActivity().getColor(R.color.white).also { window?.statusBarColor = it }

        binding = FragmentLoginBinding.inflate(layoutInflater)
        binding.progressBar.isVisible = false
        val repo = Repo()
        val viewModelFactory = ViewModelFactory(repo)
        mainViewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        // Inflate the layout for this fragment
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            var email: String = ""
            var password: String = ""
            btnLogin.setOnClickListener {
                binding.apply {
                    if (!editTextEmail.text.isNullOrEmpty() && !editTextPassword.text.isNullOrEmpty()) {
                        email = editTextEmail.text.toString()
                        password = editTextPassword.text.toString()

                        checkInternetStatus(email,password)
                    } else {
                        showToast("Input Fields are Empty.")
                    }
                }

            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun checkInternetStatus(email: String, password: String) {
        if (!isOnline()) {
            showToast(getString(R.string.no_internet))
            val snack = Snackbar.make(requireView(),
                getString(R.string.enable_wifi),
                Snackbar.LENGTH_LONG)
            snack.setAction(getString(R.string.settings)) {
                startActivity(Intent(Settings.ACTION_WIFI_SETTINGS))
            }
            snack.show()
        } else if (isOnline()) {
            checkCredentials(email,password)
        }
    }

    private fun checkCredentials(email: String, password: String) {
        val loginDetails = Login(
            "test.token.test",
            "ANDROID",
            "password",
            password,
            email
        )
        mainViewModel.pushPost(loginDetails)
        mainViewModel.myResponse.observe(viewLifecycleOwner) { response ->

                if (response.isSuccessful) {
                    try {
                    binding.progressBar.isVisible = true
                    findNavController().navigate(
                        LoginFragmentDirections.actionLoginFragmentToHomeFragment()
                    )
                    binding.progressBar.isVisible = false
                    }catch (e: Exception){
                        Toast.makeText(requireContext(), e.toString(), Toast.LENGTH_SHORT)
                    }
                } else if(!response.isSuccessful) {
                    showToast("Email or Password is not correct!\n Try again!!")
                    binding.apply {
                        editTextEmail.setText("")
                        editTextPassword.setText("")
                    }
                }

        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun isOnline(): Boolean {
        val connectivityManager =
            requireActivity().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }
            }
        }
        return false
    }


    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}
