package com.tuwaiq.bookfinder.ui.Adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.tuwaiq.bookfinder.R
import com.tuwaiq.bookfinder.data.model.Favorite
import com.tuwaiq.bookfinder.ui.FavoriteFragmentDirections
import com.tuwaiq.bookfinder.ui.MainVM
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FavoriteAdapter(
    private val booksDataFav: List<Favorite>,
    val vm: MainVM,
    val scaleUp: Animation
) : RecyclerView.Adapter<CustomHolderFav>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomHolderFav {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.gride_favorit_book_item, parent, false)
        return CustomHolderFav(view)
    }

    @SuppressLint("SetTextI18n", "NotifyDataSetChanged")
    override fun onBindViewHolder(holder: CustomHolderFav, position: Int) {

        val book = booksDataFav[position]

        //==========
        var bookPublisherDatePrint = ""

        book.publishedDate?.let {
            bookPublisherDatePrint = book.publishedDate
        }
        holder.bookPublisherDateTV.text = bookPublisherDatePrint

//===
        holder.bookTitleTV.text = book.title.toString()

        holder.bookImageIV.load(book.imageLinks)
        //?.replace("zoom=1","zoom=4"))
        //replace("http","https"))


        // if we want to add the book favorite list
        holder.likeIV.setOnClickListener {
            GlobalScope.launch {
                holder.likeIV.startAnimation(scaleUp)
                delay(300)

                if (!book.isFavBook) {
                    holder.likeIV.setImageResource(R.drawable.favorite_filled)
                    book.isFavBook = !book.isFavBook

                    val favInfo = Favorite(
                        book.id,
                        book.title,
                        book.subtitle,
                        book.authors,
                        book.publishedDate,
                        book.description,
                        book.pageCount,
                        book.previewLink,
                        book.categories,
                        book.imageLinks,
                        book.isFavBook
                    )

                    vm.saveBookToFavorite(favInfo)

                } else {
                    holder.likeIV.setImageResource(R.drawable.favorite_border)
                    book.isFavBook = !book.isFavBook
                    vm.deleteFavBooks(book.id)

                }

            }


        }

        // if we click on the item
        holder.itemView.setOnClickListener { view ->
            val action =
                FavoriteFragmentDirections.actionFavoriteFragmentToFavoraiteDetailsFragment(book)
            view.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return booksDataFav.size
    }

}

class CustomHolderFav(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    val bookTitleTV: TextView = itemView.findViewById(R.id.tvBookTitle)

    //val bookCategoryTV: TextView = itemView.findViewById(R.id.tvCategories)
    // val bookAuthorTV: TextView = itemView.findViewById(R.id.tvAuthors)
    val bookPublisherDateTV: TextView = itemView.findViewById(R.id.tvPublishDate)
    val bookImageIV: ImageView = itemView.findViewById(R.id.ivbook)
    val likeIV: ImageView = itemView.findViewById(R.id.ivLike)

    init {
        itemView.setOnClickListener(this)

    }

    override fun onClick(view: View?) {
        Toast.makeText(itemView.context, "${bookTitleTV.text} clicked", Toast.LENGTH_SHORT)
            .show()

    }

}