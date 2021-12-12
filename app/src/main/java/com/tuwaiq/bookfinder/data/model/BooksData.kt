package com.tuwaiq.bookfinder.data.model

data class BooksData (
    // val kind : String,
    //val totalItems : Int,
    val items : List<VolumeInfo>  // ------- needed--------//
)

data class VolumeInfo (
  //  val kind : String,
    val id : String,
    val volumeInfo : BookInfo,
)


data class ImageLinks (
    val smallThumbnail : String,
    val thumbnail : String
)

data class BookInfo(


    val title : String?,  // ------- needed--------//
     val subtitle : String?,  // ------- needed--------//
    val authors : List<String>?,
    //val publisher : String?,
    val publishedDate : String,
    val description : String?,
    val pageCount : Int?,
   // val infoLink : String,
    val previewLink : String?,   // ------- needed--------//
    //val buyLink : String,  // ------- needed--------//
    // SA en
  //  val language : String,
    // for filter
    val categories : List<String>?,
    // for book image
    val imageLinks : ImageLinks?,   // ------- needed--------//
    // magazene or book
    //val isEbook : Boolean,
    // pdf web link
    //val webReaderLink : String,

)



