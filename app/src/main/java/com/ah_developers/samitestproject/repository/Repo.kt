package com.ah_developers.samitestproject.repository

import com.ah_developers.samitestproject.api.RetrofitInstance
import com.ah_developers.samitestproject.model.Login
import retrofit2.Response

class Repo {
    suspend fun pushDetails(post: Login): Response<Login>{
        return RetrofitInstance.api.pushPost(post)
    }
}