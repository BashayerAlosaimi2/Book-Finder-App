package com.tuwaiq.bookfinder.data.network

import com.tuwaiq.bookfinder.Util.Constants.Companion.API_KEY
import com.tuwaiq.bookfinder.data.model.BooksData
import retrofit2.http.GET
import retrofit2.http.Query

interface BookAPI {

    @GET("?q=ebook&key=$API_KEY")
    suspend fun fetchBooks(): BooksData

    @GET("?printType=books&$API_KEY")
    suspend fun searchBook(@Query("q") searchKey: String): BooksData

}
