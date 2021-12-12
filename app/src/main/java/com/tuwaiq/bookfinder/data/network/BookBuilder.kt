package com.tuwaiq.bookfinder.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

    object BookBuilder {
        private const val BASE_URL = "https://www.googleapis.com/books/v1/volumes/"
        private fun retrofit(): Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        //local API to call global API
        val bookAPI: BookAPI = retrofit().create(BookAPI::class.java)
    }
