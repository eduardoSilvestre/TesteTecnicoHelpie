package com.eduardo.desafiotecnico.service

import com.eduardo.desafiotecnico.model.UserResponse
import retrofit2.Call
import retrofit2.http.GET

interface UserApi {
    @GET("users")
    fun getUsers(): Call<UserResponse>
}