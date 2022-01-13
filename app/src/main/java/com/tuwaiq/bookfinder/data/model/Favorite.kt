package com.tuwaiq.bookfinder.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Favorite(

    val id : String="",
    val title : String?="",
    val authors : List<String>?  = listOf(""),
    val publishedDate : String?="",
    val description : String?="",
    val pageCount : Int?=-1,
    val previewLink : String?="",
    val categories : List<String>? = listOf(""),
    val imageLinks : String= "",
    var isFavBook: Boolean = true
):Parcelable
