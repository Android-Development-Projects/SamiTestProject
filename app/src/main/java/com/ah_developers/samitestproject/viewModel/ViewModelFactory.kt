package com.ah_developers.samitestproject.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ah_developers.samitestproject.repository.Repo

class ViewModelFactory(
    var repo: Repo
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = MainViewModel(repo) as T
}