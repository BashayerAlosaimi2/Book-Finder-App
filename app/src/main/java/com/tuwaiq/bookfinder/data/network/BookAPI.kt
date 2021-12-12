package com.tuwaiq.bookfinder.data.network

import com.tuwaiq.bookfinder.data.model.BooksData
import com.tuwaiq.bookfinder.data.model.VolumeInfo
import retrofit2.http.GET
import retrofit2.http.Query

interface BookAPI {
    //we should implement here the four CRUD functions for REST
    @GET("?q=ebooks&key=AIzaSyCHgIbNs2LqtVCmdb2eCo2erGEf_avbU-g")
    suspend fun fetchBooks(): BooksData

    @GET("?key=AIzaSyCHgIbNs2LqtVCmdb2eCo2erGEf_avbU-g")
    suspend fun searchBook(@Query("q") searchKey:String ): BooksData
}
