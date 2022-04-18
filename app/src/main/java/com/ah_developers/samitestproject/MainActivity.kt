package com.ah_developers.samitestproject

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import com.ah_developers.samitestproject.databinding.ActivityMainBinding
import com.ah_developers.samitestproject.repository.Repo
import com.ah_developers.samitestproject.viewModel.MainViewModel
import com.ah_developers.samitestproject.viewModel.ViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repo = Repo()
        val viewModelFactory = ViewModelFactory(repo)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        installSplashScreen().apply {
            setVisible(
                viewModel.isLoading.value
            )
        }
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}