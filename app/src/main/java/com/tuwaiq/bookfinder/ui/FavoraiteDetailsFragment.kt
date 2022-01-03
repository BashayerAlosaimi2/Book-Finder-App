package com.tuwaiq.bookfinder.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import coil.load
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.tuwaiq.bookfinder.R


class FavoraiteDetailsFragment : BottomSheetDialogFragment() {
    override fun getTheme(): Int = R.style.BottomSheetDialogTheme

    private lateinit var titleTV: TextView
    private lateinit var title2TV: TextView
    private lateinit var subtitleTV: TextView
    private lateinit var descriptionTV: TextView
    private lateinit var authorsTV: TextView
    private lateinit var categoryTV: TextView
    private lateinit var PageCountTV: TextView
    private lateinit var PublishDateTV: TextView
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book_details, container, false)
    }

    @SuppressLint("CutPasteId")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bookImgV = view.findViewById(R.id.ivbookD)
        titleTV = view.findViewById(R.id.tvTitle1D)
        title2TV = view.findViewById(R.id.tvTitle2D)
        subtitleTV = view.findViewById(R.id.tvSubTitleD)
        descriptionTV = view.findViewById(R.id.tvDescriptionD)
        authorsTV = view.findViewById(R.id.tvAuthorsD)
        categoryTV = view.findViewById(R.id.tvCategoriesD)
        PageCountTV = view.findViewById(R.id.tvPageCount)
        previewBtn = view.findViewById(R.id.btnPreviewD)
        PublishDateTV = view.findViewById(R.id.tvPublishDateD)


        //show data

        bookImgV.load(args.favoraiteBooksKey.imageLinks)//?.replace("zoom=1","zoom=0"))
        titleTV.text = args.favoraiteBooksKey.title
        title2TV.text = args.favoraiteBooksKey.title

        //========
        var subTitle = ""
        args.favoraiteBooksKey.subtitle?.let {
            subTitle = args.favoraiteBooksKey.subtitle.toString()
        }
        subtitleTV.text = subTitle

        // ========
        var PageCountPrint = ""
        args.favoraiteBooksKey.pageCount?.let {
            PageCountPrint = args.favoraiteBooksKey.pageCount.toString()
        }
        PageCountTV.text = PageCountPrint
        //===
        var description = "no description for this book"
        args.favoraiteBooksKey.description?.let {
            description = args.favoraiteBooksKey.description.toString()
        }
        descriptionTV.text = description
        // ========
        var authorsPrint = "unknown"
        args.favoraiteBooksKey.authors?.let {
            for (i in it) {
                if (i == it.last())
                    authorsPrint += "$i ."
                else
                    authorsPrint += "$i , "
            }
        }
        authorsTV.text = authorsPrint

        //==========
        var bookPublisherDatePrint = "unknown"
        args.favoraiteBooksKey.publishedDate?.let {
            bookPublisherDatePrint = args.favoraiteBooksKey.publishedDate.toString()
        }
        PublishDateTV.text = bookPublisherDatePrint

        //========
        var categoryPrint = "undefined"
        args.favoraiteBooksKey.categories?.let {
            for (i in it) {
                if (i == it.last())
                    categoryPrint += "$i ."
                else
                    categoryPrint += "$i , "
            }
        }
        categoryTV.text = categoryPrint
        //========

        previewUrl = args.favoraiteBooksKey.previewLink.toString()
        previewBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "uri"
            intent.putExtra("preview", previewUrl)
            startActivity(intent)
        }

    }

}