package com.ah_developers.samitestproject.api

import com.ah_developers.samitestproject.model.Login
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface Api {

    @POST("login")
    suspend fun pushPost(
        @Body post: Login
    ) : Response<Login>

}