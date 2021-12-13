package com.tuwaiq.bookfinder.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import com.tuwaiq.bookfinder.R
import com.tuwaiq.bookfinder.data.model.BooksData

class BookDetailsFragment : Fragment() {
    private lateinit var titleTV: TextView
    private lateinit var subtitleTV: TextView
    private lateinit var descriptionTV: TextView
    private lateinit var authorsTV: TextView
    private lateinit var categoryTV: TextView
    private lateinit var thumbnailTV: TextView
    private lateinit var titlePageCountTV: TextView
    private lateinit var previewLinkTV: TextView
    private lateinit var infoLinkBtn: Button
    private lateinit var buyLinkBtn: Button
    private var pageCountTV = -1
    private lateinit var bookIV: ImageView

   private val args: BookDetailsFragmentArgs by navArgs()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book_details, container, false)
    }


}