package com.tuwaiq.bookfinder.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class BooksData (

     val items : @RawValue List<VolumeInfo>
): Parcelable
@Parcelize
data class VolumeInfo (

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
    val authors : List<String>?  = listOf(""),
    val publishedDate : String,
    val description : String?,
    val pageCount : Int?,
    val previewLink : String?,
    val categories : List<String>? = listOf(""),
    val imageLinks : ImageLinks?,

): Parcelable




