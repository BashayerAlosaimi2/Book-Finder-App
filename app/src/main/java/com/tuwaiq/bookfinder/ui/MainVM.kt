package com.tuwaiq.bookfinder.ui

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.LifecycleOwner
import com.tuwaiq.bookfinder.NAME
import com.tuwaiq.bookfinder.PREFERENCE
import com.tuwaiq.bookfinder.data.model.Favorite
import com.tuwaiq.bookfinder.data.model.Users
import com.tuwaiq.bookfinder.data.model.VolumeInfo
import com.tuwaiq.bookfinder.data.network.AppRepo
import kotlinx.coroutines.launch

class MainVM(context: Application) : AndroidViewModel(context) {
    private val repo = AppRepo()

    val preferences = context.getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE)


    fun getUserDataFromRepo(){//}:Users {
       preferences.edit().putString(NAME, repo.userRetrivedData.username).apply()

        //return repo.userRetrivedData

    }

    fun fetchBooksList(searchKeyWord: String? = null): LiveData<List<VolumeInfo>> {
        val books = MutableLiveData<List<VolumeInfo>>()
        viewModelScope.launch {
            try {
                if (searchKeyWord.isNullOrEmpty()) {
                    books.postValue(repo.fetchList())
                } else {
                    books.postValue(repo.searchBooks(searchKeyWord))
                }
            } catch (e: Throwable) {
                Log.e("Books search", "Downloaded Books failed ${e.localizedMessage}")
            }
        }
        return books
    }

    fun userData(viewLifecycleOwner: LifecycleOwner) {

        viewModelScope.launch {

            repo.retrieveUserData().observe(viewLifecycleOwner,{
                preferences.edit().putString(NAME, it).apply()
            })




        }
    }

    fun fetchFavList(viewLifecycleOwner: LifecycleOwner): MutableLiveData<MutableList<Favorite>> {
        var books: MutableLiveData<MutableList<Favorite>> = MutableLiveData<MutableList<Favorite>>()

        viewModelScope.launch {
            repo.fetchFavBook().observe(viewLifecycleOwner, {
                books.value = it
            })

            Log.d("books vm in observe :", books.value.toString())
        }

/*
            books.postValue( repo.fetchFavBook())
            Log.d("books vm", "vm")
            Log.d("books vm is :", books.value.toString())
        }*/
        Log.d("books vm run  :", books.value.toString())
        return books
    }

    fun saveUserData(user: Users) {
        viewModelScope.launch {
            repo.saveUserData(user)
        }
    }

    fun saveBookToFavorite(favBook: Favorite) {
        viewModelScope.launch {
            repo.saveBookToFavorite(favBook)
        }
    }

    fun deleteFavBooks(favBookId: String) {
        viewModelScope.launch {
            repo.deleteFavBooks(favBookId)
        }
    }
/*    fun checkIfFavorite(favBookId: String):MutableLiveData<Boolean> {
        var isFav:MutableLiveData<Boolean> = MutableLiveData<Boolean>()
        viewModelScope.launch {
           isFav =  repo.checkIfFavorite(favBookId)
        }
        return isFav
    }*/

      fun updateUserName(userName: String) {
          viewModelScope.launch {
              repo.updateUserName(userName)
              repo.retrieveUserData()
          }
      }
}
