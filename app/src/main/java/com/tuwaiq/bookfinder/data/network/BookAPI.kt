package com.tuwaiq.bookfinder.data.network

import com.tuwaiq.bookfinder.data.model.BooksData
import com.tuwaiq.bookfinder.data.model.VolumeInfo
import retrofit2.http.GET
import retrofit2.http.Query

interface BookAPI {
    //we should implement here the four CRUD functions for REST
    @GET("?q=sport+inauthor+&key=AIzaSyCHgIbNs2LqtVCmdb2eCo2erGEf_avbU-g")
    suspend fun fetchBooks(): BooksData

    @GET("?printType=books&key=AIzaSyCHgIbNs2LqtVCmdb2eCo2erGEf_avbU-g")
    suspend fun searchBook(@Query("q") searchKey: String): BooksData

    /*@GET("?printType=books&key=AIzaSyCHgIbNs2LqtVCmdb2eCo2erGEf_avbU-g")
    suspend fun searchBook2(@Query("q") searchKey: String): VolumeInfo
*/
    /*   @GET("?printType=books&key=AIzaSyCHgIbNs2LqtVCmdb2eCo2erGEf_avbU-g")
       suspend fun fetchFavBooks(@Query("q") bookId: String): BooksData
   */
/*
    @GET("?as_coll=0&key=AIzaSyCHgIbNs2LqtVCmdb2eCo2erGEf_avbU-g")
    suspend fun fetchFavoriteBooks(@Query("uid") uid: String): BooksData

    @POST("?v1/mylibrary/bookshelves/0/NRWlitmahXkC&key=AIzaSyCHgIbNs2LqtVCmdb2eCo2erGEf_avbU-g")
    suspend fun addFavoriteBooks(@Query("addVolume") volumeID: String): BooksData
*/

/*Authorization: *//* auth token here *//*
Content-Type: application/json
Content-Length: CONTENT_LENGTH*/
}
