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
import androidx.core.net.toUri
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.tuwaiq.bookfinder.R
import com.tuwaiq.bookfinder.data.model.BooksData
import kotlinx.android.synthetic.main.fragment_book_details.view.*

class BookDetailsFragment : BottomSheetDialogFragment() {
    override fun getTheme(): Int = R.style.BottomSheetDialogTheme

    private lateinit var titleTV: TextView
    private lateinit var title2TV: TextView
    private lateinit var subtitleTV: TextView
    private lateinit var descriptionTV: TextView
    private lateinit var authorsTV: TextView
    private lateinit var categoryTV: TextView
    private lateinit var PublishDateTV: TextView
    private lateinit var PageCountTV: TextView
    private lateinit var previewBtn: Button
    private lateinit var bookImgV: ImageView
    private lateinit var previewUrl: String

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

        bookImgV.load(args.booksInfoKey.imageLinks?.thumbnail)//?.replace("zoom=1","zoom=0"))
        titleTV.setText(args.booksInfoKey.title)
        title2TV.setText(args.booksInfoKey.title)

        var subTitle = ""
        args.booksInfoKey.subtitle?.let {
            subTitle = args.booksInfoKey.subtitle.toString()
        }
        subtitleTV.text = subTitle
        //==
        var description = "no description for this book"
        args.booksInfoKey.description?.let {
            description = args.booksInfoKey.description.toString()
        }
        descriptionTV.text = description

        var PageCountPrint = ""
        args.booksInfoKey.pageCount?.let {
            PageCountPrint = args.booksInfoKey.pageCount.toString()
        }
        PageCountTV.text = PageCountPrint

        var authorsPrint = ""
        args.booksInfoKey.authors?.let {
            for (i in it) {
                if (i == it.last())
                    authorsPrint += "$i ."
                else
                    authorsPrint += "$i , "
            }
        }

        var categoryPrint = ""
        args.booksInfoKey.categories?.let {
            for (i in it) {
                if (i == it.last())
                    categoryPrint += "$i ."
                else
                    categoryPrint += "$i , "
            }
        }
        var pageCountPrint = ""
        args.booksInfoKey.pageCount?.let {
            pageCountPrint = args.booksInfoKey.pageCount.toString()

        }


        var publishedDatePrint = " "
        args.booksInfoKey.publishedDate?.let {
            publishedDatePrint = args.booksInfoKey.pageCount.toString()

        }
        PublishDateTV.text= publishedDatePrint

        authorsTV.text= authorsPrint
        categoryTV.text = categoryPrint
        PageCountTV.text= pageCountPrint


        previewUrl = args.booksInfoKey.previewLink.toString()
        previewBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW,previewUrl.toUri())
           // intent.type = "url"
            //intent.putExtra("preview", previewUrl)
            startActivity(intent)
        }

    }


}