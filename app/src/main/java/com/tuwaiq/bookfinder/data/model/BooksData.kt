package com.tuwaiq.bookfinder.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class BooksData (
    // val kind : String,
    //val totalItems : Int,
     val items : @RawValue List<VolumeInfo>  // ------- needed--------//
): Parcelable
@Parcelize
data class VolumeInfo (
  //  val kind : String,
    val id : String,
    val volumeInfo : BookInfo
): Parcelable{
    var isFavBook: Boolean = false
}

@Parcelize
data class ImageLinks (
    val smallThumbnail : String,
    val thumbnail : String
): Parcelable
@Parcelize
data class BookInfo(

    val title : String?,
     val subtitle : String?,
    val authors : List<String>?  = listOf(""),
    //val publisher : String?,
    val publishedDate : String,
    val description : String?,
    val pageCount : Int?,
   // val infoLink : String,
    val previewLink : String?,
    //val buyLink : String,
    val categories : List<String>? = listOf(""),
    // for book image
    val imageLinks : ImageLinks?,
    // pdf web link
    //val webReaderLink : String,

): Parcelable




