package com.ah_developers.samitestproject.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ah_developers.samitestproject.model.Login
import com.ah_developers.samitestproject.repository.Repo
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import retrofit2.Response

class MainViewModel(private val repo : Repo) : ViewModel() {
    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()
    val myResponse = MutableLiveData<Response<Login>>()

    init {
           viewModelScope.launch {
               delay(3000)
               _isLoading.value = false
           }
    }

    fun pushPost(post: Login){
        viewModelScope.launch {
            val response = repo.pushDetails(post)
            myResponse.value = response
        }
    }
}