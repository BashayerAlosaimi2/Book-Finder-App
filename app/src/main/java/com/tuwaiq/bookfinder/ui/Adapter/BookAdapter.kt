package com.tuwaiq.bookfinder.ui.Adapter

import android.view.LayoutInflater
import android.view.View
import com.bumptech.glide.Glide
import android.view.ViewGroup
import android.view.animation.Animation
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.tuwaiq.bookfinder.R
import com.tuwaiq.bookfinder.data.model.Favorite
import com.tuwaiq.bookfinder.data.model.VolumeInfo
import com.tuwaiq.bookfinder.ViewModel.MainVM
import com.tuwaiq.bookfinder.ui.fragment.HomeDirections
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class BookAdapter(
    private val booksData: List<VolumeInfo>,
    val vm: MainVM,
    val scaleUp: Animation,
) : RecyclerView.Adapter<CustomHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.gride_book_item, parent, false)
        return CustomHolder(view)
    }

    override fun onBindViewHolder(holder: CustomHolder, position: Int) {
        val uid = FirebaseAuth.getInstance().currentUser?.uid
        val db = FirebaseFirestore.getInstance()

        val book = booksData[position]

        holder.bookTitleTV.text = book.volumeInfo.title.toString()

        var bookPublisherDatePrint = ""

        book.volumeInfo.publishedDate.let {
            bookPublisherDatePrint = book.volumeInfo.publishedDate
        }
        holder.bookPublisherDateTV.text = bookPublisherDatePrint

        Glide.with(holder.itemView)
            .load(book.volumeInfo.imageLinks?.smallThumbnail)
            .placeholder(R.drawable.imagenotfound2)
            .error(R.drawable.placeholder)
            .into(holder.bookImageIV)

        db.collection("Users").document("$uid").collection("Favorite").document(book.id).get()
            .addOnCompleteListener {
                if (it.result?.exists()!!) {
                    book.isFavBook=true
                    holder.likeIV.setImageResource(R.drawable.favorite_filled)
                } else {
                    holder.likeIV.setImageResource(R.drawable.favorite_border)

                }
            }

        holder.likeIV.setOnClickListener {
            GlobalScope.launch {
                holder.likeIV.startAnimation(scaleUp)
                delay(500)
            }

                if (!book.isFavBook) {
                    holder.likeIV.setImageResource(R.drawable.favorite_filled)
                    book.isFavBook = !book.isFavBook

                    val favInfo = Favorite(
                        book.id,
                        book.volumeInfo.title,
                        book.volumeInfo.authors,
                        book.volumeInfo.publishedDate,
                        book.volumeInfo.description,
                        book.volumeInfo.pageCount,
                        book.volumeInfo.previewLink,
                        book.volumeInfo.categories,
                        book.volumeInfo.imageLinks?.thumbnail.toString(),
                        book.isFavBook

                    )

                    vm.saveBookToFavorite(favInfo)

                }else {
                    holder.likeIV.setImageResource(R.drawable.favorite_border)
                    book.isFavBook = !book.isFavBook
                    vm.deleteFavBooks(book.id)

                }
        }

        holder.itemView.setOnClickListener { view ->
            val action =
                HomeDirections.actionBookFragmentToBookDetailsFragment(book.volumeInfo)
            view.findNavController().navigate(action)
        }
 }

    override fun getItemCount(): Int {
        return booksData.size
    }
}

class CustomHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    val bookTitleTV: TextView = itemView.findViewById(R.id.tvBookTitle)
    val bookPublisherDateTV: TextView = itemView.findViewById(R.id.tvPublishDate)
    val bookImageIV: ImageView = itemView.findViewById(R.id.ivbook)
    val likeIV: ImageView = itemView.findViewById(R.id.ivLike)

    init {
        itemView.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        }

    }

