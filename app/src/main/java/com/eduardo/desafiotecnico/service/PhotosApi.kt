package com.eduardo.desafiotecnico.service

import com.eduardo.desafiotecnico.model.PhotoResponse
import retrofit2.Call
import retrofit2.http.GET

interface PhotosApi {

    @GET("photos")
    fun getPhotos(): Call<PhotoResponse>
}