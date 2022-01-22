package com.tuwaiq.bookfinder.ui.fragment

import android.content.Intent
import android.os.Bundle
import com.bumptech.glide.Glide
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.tuwaiq.bookfinder.R


class FavoraiteDetailsFragment : BottomSheetDialogFragment() {
    override fun getTheme(): Int = R.style.BottomSheetDialogTheme

    private lateinit var titleTV: TextView
    private lateinit var title2TV: TextView

    private lateinit var descriptionTV: TextView

    private lateinit var authorsTV: TextView
    private lateinit var authorsTV0: TextView

    private lateinit var categoryTV: TextView
    private lateinit var categoryTV0: TextView

    private lateinit var PageCountTV: TextView
    private lateinit var PageCountTV0: TextView

    private lateinit var PublishDateTV: TextView
    private lateinit var PublishDateTV0: TextView

    private lateinit var previewBtn: Button
    private lateinit var bookImgV: ImageView
    private lateinit var previewUrl: String

    private val args: FavoraiteDetailsFragmentArgs by navArgs()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_book_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bookImgV = view.findViewById(R.id.ivbookD)
        previewBtn = view.findViewById(R.id.btnPreviewD)
        descriptionTV = view.findViewById(R.id.tvDescriptionD)

        titleTV = view.findViewById(R.id.tvTitle1D)
        title2TV = view.findViewById(R.id.tvTitle2D)

        authorsTV = view.findViewById(R.id.tvAuthorsD)
        authorsTV = view.findViewById(R.id.tvAuthorsD0)

        categoryTV = view.findViewById(R.id.tvCategoriesD)
        categoryTV0 = view.findViewById(R.id.tvCategoriesD0)

        PageCountTV = view.findViewById(R.id.tvPageCountD)
        PageCountTV0 = view.findViewById(R.id.tvPageCountD0)

        PublishDateTV = view.findViewById(R.id.tvPublishDateD)
        PublishDateTV0 = view.findViewById(R.id.tvPublishDateD0)
        Glide.with(view)
            .load(args.favoraiteBooksKey.imageLinks)
            .placeholder(R.drawable.imagenotfound2)
            .error(R.drawable.placeholder)
            .into(bookImgV)


      //  bookImgV.load(args.favoraiteBooksKey.imageLinks)//?.replace("zoom=1","zoom=0"))
        titleTV.text = args.favoraiteBooksKey.title
        title2TV.text = args.favoraiteBooksKey.title

        if (args.favoraiteBooksKey.pageCount == null) {
            PageCountTV.isVisible = false
            PageCountTV0.isVisible = false
        } else {
            PageCountTV.text = args.favoraiteBooksKey.pageCount.toString()
        }
        if (args.favoraiteBooksKey.publishedDate == null) {
            PublishDateTV.isVisible = false
            PublishDateTV0.isVisible = false
        } else {
            PublishDateTV.text = args.favoraiteBooksKey.publishedDate.toString()
        }

        if (args.favoraiteBooksKey.categories == null) {
            categoryTV.isVisible = false
            categoryTV0.isVisible = false

        } else {
            var categoryPrint = ""
            args.favoraiteBooksKey.categories?.let {
                for (i in it) {
                    if (i == it.last())
                        categoryPrint += "$i ."
                    else
                        categoryPrint += "$i , "
                }
            }
            categoryTV.text = categoryPrint
        }

        if (args.favoraiteBooksKey.authors == null) {
            authorsTV.isVisible = false
            authorsTV0.isVisible = false

        } else {
            var authorsPrint = ""
            args.favoraiteBooksKey.authors?.let {
                for (i in it) {
                    if (i == it.last())
                        authorsPrint += "$i ."
                    else
                        authorsPrint += "$i , "
                }
            }
            authorsTV.text = authorsPrint
        }
        if (args.favoraiteBooksKey.description == null) {
            descriptionTV.text = getString(R.string.no_description)
        } else {
            descriptionTV.text = args.favoraiteBooksKey.description.toString()
        }
        previewUrl = args.favoraiteBooksKey.previewLink.toString()
        previewBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, previewUrl.toUri())
            startActivity(intent)
        }

    }

}