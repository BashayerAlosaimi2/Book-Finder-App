package com.tuwaiq.bookfinder.data.network

import com.tuwaiq.bookfinder.data.model.BookInfo
import com.tuwaiq.bookfinder.data.model.BooksData
import com.tuwaiq.bookfinder.data.model.ImageLinks
import com.tuwaiq.bookfinder.data.model.VolumeInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BookRepo {

    /* var img = ImageLinks("http://books.google.com/books/content?id=UYNMAAAAMAAJ&printsec=frontcover&img=1&zoom=5&source=gbs_api","http://books.google.com/books/content?id=UYNMAAAAMAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api")
     var y = BookInfo("TITLE","SUB",listOf("AUTH"),"1/1/1","1",12,"BN",listOf(""),img)
     var i = VolumeInfo("1",y)
     var mylist :List<VolumeInfo> = listOf(i)*/

    private val api1 = BookBuilder.bookAPI
    suspend fun fetchList(): List<VolumeInfo> = withContext(Dispatchers.IO) {
        api1.fetchBooks().items
        }
          suspend fun searchBooks(searchKeyWord: String): List<VolumeInfo>  = withContext(Dispatchers.IO) {
              api1.searchBook(searchKeyWord).items
          }


    }
