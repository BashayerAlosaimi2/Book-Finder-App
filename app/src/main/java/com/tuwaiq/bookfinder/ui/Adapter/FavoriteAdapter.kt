package com.tuwaiq.bookfinder.ui.Adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import com.bumptech.glide.Glide
import android.view.ViewGroup
import android.view.animation.Animation
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.tuwaiq.bookfinder.R
import com.tuwaiq.bookfinder.data.model.Favorite
import com.tuwaiq.bookfinder.ViewModel.MainVM
import com.tuwaiq.bookfinder.ui.fragment.FavoriteDirections
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FavoriteAdapter(
    private val booksDataFav: MutableList<Favorite>,
    val vm: MainVM,
    val scaleUp: Animation,
    val favIcon: ImageView,
    val favText: TextView
) : RecyclerView.Adapter<CustomHolderFav>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomHolderFav {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.gride_favorit_book_item, parent, false)
        return CustomHolderFav(view)
    }

    @SuppressLint("NotifyDataSetChanged")
    @DelicateCoroutinesApi
    override fun onBindViewHolder(holder: CustomHolderFav, position: Int) {
        val book = booksDataFav[position]

        var bookPublisherDatePrint = ""

        book.publishedDate?.let {
            bookPublisherDatePrint = book.publishedDate
        }
        holder.bookPublisherDateTV.text = bookPublisherDatePrint
        holder.bookTitleTV.text = book.title.toString()

        Glide.with(holder.itemView)
            .load(book.imageLinks)
            .placeholder(R.drawable.imagenotfound2)
            .error(R.drawable.placeholder)
            .into(holder.bookImageIV)

        holder.likeIV.setOnClickListener {
            GlobalScope.launch {
                holder.likeIV.startAnimation(scaleUp)
                delay(300)
            }
            if (book.isFavBook) {
                book.isFavBook = !book.isFavBook
                vm.deleteFavBooks(book.id)
                booksDataFav.removeAt(position)
                notifyDataSetChanged()

                if (getItemCount() == 0) {
                    favIcon.setVisibility(View.VISIBLE)
                    favText.setVisibility(View.VISIBLE)

                }
            }
        }

        holder.itemView.setOnClickListener { view ->
            val action =
                FavoriteDirections.actionFavoriteFragmentToFavoraiteDetailsFragment(book)
            view.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return booksDataFav.size
    }

}

class CustomHolderFav(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    val bookTitleTV: TextView = itemView.findViewById(R.id.tvBookTitle)
    val bookPublisherDateTV: TextView = itemView.findViewById(R.id.tvPublishDate)
    val bookImageIV: ImageView = itemView.findViewById(R.id.ivbook)
    val likeIV: ImageView = itemView.findViewById(R.id.ivLike)

    init {
        itemView.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        likeIV.setOnClickListener {

            Toast.makeText(
                itemView.context,
                "${bookTitleTV.text} added to favorite",
                Toast.LENGTH_SHORT
            )
                .show()

        }

    }

}