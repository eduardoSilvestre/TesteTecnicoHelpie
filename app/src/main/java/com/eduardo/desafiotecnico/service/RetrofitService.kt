package com.eduardo.desafiotecnico.service

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class RetrofitService {

    companion object {
        private var retrofit: Retrofit? = null
        private val BASE_URL = "https://jsonplaceholder.typicode.com/"

        private fun getRetrofit(): Retrofit? {
            if (retrofit == null) {
                val clientBuilder = OkHttpClient.Builder()
                clientBuilder.connectTimeout(30, TimeUnit.SECONDS)
                clientBuilder.readTimeout(30, TimeUnit.SECONDS)
                clientBuilder.writeTimeout(30, TimeUnit.SECONDS)

                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(clientBuilder.build())
                    .build()

            }
            return retrofit
        }

        fun getUserService(): UserApi = getRetrofit()!!.create(
            UserApi::class.java)

        fun getPhotosService(): PhotosApi = getRetrofit()!!.create(
            PhotosApi::class.java
        )
    }
}


