package com.tuwaiq.bookfinder.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.animation.AnimationUtils
import android.widget.SearchView
import androidx.fragment.app.Fragment
import com.tuwaiq.bookfinder.R
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tuwaiq.bookfinder.ViewModel.MainVM
import com.tuwaiq.bookfinder.ui.Adapter.BookAdapter

class Home : Fragment() {

    private lateinit var booksRV: RecyclerView
    private lateinit var searchView: SearchView

    private val vm by lazy {
        ViewModelProvider(this)[MainVM::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_book, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm.userData(viewLifecycleOwner)

        searchView = view.findViewById(R.id.search)
        booksRV = view.findViewById(R.id.rvBooks)
        booksRV.layoutManager =
            GridLayoutManager(context, 2)
        loadBooks()
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.d(tag, "Query text : $query")
                loadBooks(query?.trim())
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Log.d(tag, "Query text : $newText")
                return false
            }
        })
    }

    private fun loadBooks(query: String? = null) {
        val scaleUp = AnimationUtils.loadAnimation(context, R.anim.scale_up)
        vm.fetchBooksList(query).observe(viewLifecycleOwner, {
            if (query.isNullOrEmpty()) {

                booksRV.adapter = BookAdapter(it, vm, scaleUp)
            } else {
                booksRV.scrollToPosition(0)
                booksRV.swapAdapter(BookAdapter(it, vm, scaleUp), false)
            }
            Log.d("Google books main Response", it.toString())
        })
    }
}

