package com.tuwaiq.bookfinder.ui

import android.util.Log

import com.tuwaiq.bookfinder.R


import android.os.Bundle
import android.view.*
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.tuwaiq.bookfinder.ui.Adapter.FavoriteAdapter

class FavoriteFragment : Fragment() {
    private lateinit var booksRVFav: RecyclerView
    private lateinit var favAdapter : FavoriteAdapter

    private val vm by lazy {
        ViewModelProvider(requireActivity())[MainVM::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*  // to enable OptionsMenu
          setHasOptionsMenu(true)*/
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        booksRVFav = view.findViewById(R.id.rvFavBooks)
        booksRVFav.layoutManager = GridLayoutManager(context,2)
        loadBooks()
    }


    private fun loadBooks() {

        Log.d("Favorite books main Response", "waiting")

        val scaleUp = AnimationUtils.loadAnimation(context,R.anim.scale_up)


             vm.fetchFavList(viewLifecycleOwner).observe(viewLifecycleOwner, {

                favAdapter= FavoriteAdapter(it, vm , scaleUp)
                booksRVFav.adapter = favAdapter
               // favAdapter.notifyDataSetChanged()

            Log.d("Favorite books main Response", it.toString())
        })

    }




}

