package com.ah_developers.samitestproject.model

data class Login(
    val device_token: String,
    val device_type: String,
    val grant_type: String,
    val password: String,
    val username: String
)