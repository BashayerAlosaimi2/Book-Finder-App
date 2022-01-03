package com.tuwaiq.bookfinder.ui

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
import com.tuwaiq.bookfinder.ui.Adapter.BookAdapter

class BookFragment : Fragment() {
    private lateinit var booksRV: RecyclerView
    private val vm by lazy {
        ViewModelProvider(requireActivity())[MainVM::class.java]
    }
    //private lateinit var bookFavList: MutableList<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // to enable OptionsMenu
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

         vm.userData()

        booksRV = view.findViewById(R.id.rvBooks)
        booksRV.layoutManager  =
            GridLayoutManager(context,2)
        loadBooks()
    }

    private fun loadBooks(query: String? = null) {
        var scaleUp = AnimationUtils.loadAnimation(context,R.anim.scale_up)
        vm.fetchBooksList(query).observe(viewLifecycleOwner, {
            if (query.isNullOrEmpty()) {


                booksRV.adapter = BookAdapter(it,vm,scaleUp)
            } else {
                //to start from the beginning of the screen
                booksRV.scrollToPosition(0)
                booksRV.swapAdapter(BookAdapter(it, vm, scaleUp), false)
            }
            Log.d("Google books main Response", it.toString())
        })    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        val tag = "SearchView"

        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.search_menu, menu)
        val searchIcon: MenuItem = menu.findItem(R.id.app_bar_search)
        val searchView = searchIcon.actionView as SearchView
        searchView.apply {

            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
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

    }




}