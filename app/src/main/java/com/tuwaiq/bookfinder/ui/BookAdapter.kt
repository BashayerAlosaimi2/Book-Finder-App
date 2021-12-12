package com.tuwaiq.bookfinder.ui

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.squareup.picasso.Picasso
import com.tuwaiq.bookfinder.R
import com.tuwaiq.bookfinder.data.model.VolumeInfo

class BookAdapter(private val booksData: List<VolumeInfo>) : RecyclerView.Adapter<CustomHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.rv_books_item, parent, false)
        return CustomHolder(view)
    }

    override fun onBindViewHolder(holder: CustomHolder, position: Int) {
        val Book = booksData[position].volumeInfo
        holder.bookTitleTV.text = Book.title.toString()
        holder.bookCategoryTV.text = Book.categories.toString()
        holder.bookAuthorTV.text = Book.authors.toString()
        holder.bookPublisherDateTV.text = Book.publishedDate
       holder.bookImageIV.load(Book.imageLinks?.smallThumbnail)//?.replace("http","https"))
      // Picasso.get().load(Book.imageLinks?.smallThumbnail).into(holder.bookImageIV)

    }

    override fun getItemCount(): Int {
        return booksData.size
    }
}

class CustomHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val bookTitleTV: TextView = itemView.findViewById(R.id.tvBookTitle)
    val bookCategoryTV: TextView = itemView.findViewById(R.id.tvCategories)
    val bookAuthorTV: TextView = itemView.findViewById(R.id.tvAuthors)
    val bookPublisherDateTV: TextView = itemView.findViewById(R.id.tvPublishDate)
    val bookImageIV: ImageView = itemView.findViewById(R.id.ivbook)

}