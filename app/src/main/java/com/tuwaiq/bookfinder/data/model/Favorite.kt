package com.tuwaiq.bookfinder.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Favorite(

    val id : String="",  // ------- needed--------//
    val title : String?="",  // ------- needed--------//
    val subtitle : String?="",  // ------- needed--------//
    val authors : List<String>?  = listOf(""),
    //val publisher : String?,
    val publishedDate : String?="",
    val description : String?="",
    val pageCount : Int?=-1,
    // val infoLink : String,
    val previewLink : String?="",   // ------- needed--------//
   // val buyLink : String?="",  // ------- needed--------//
    val categories : List<String>? = listOf(""),
    // for book image
    val imageLinks : String= "",
    var isFavBook: Boolean = true
    // pdf web link
    //val webReaderLink : String,


):Parcelable
