package com.tuwaiq.bookfinder.ViewModel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.LifecycleOwner
import com.tuwaiq.bookfinder.Util.Constants.Companion.EMAIL
import com.tuwaiq.bookfinder.Util.Constants.Companion.NAME
import com.tuwaiq.bookfinder.Util.Constants.Companion.PREFERENCE
import com.tuwaiq.bookfinder.data.model.Favorite
import com.tuwaiq.bookfinder.data.model.Users
import com.tuwaiq.bookfinder.data.model.VolumeInfo
import com.tuwaiq.bookfinder.data.network.AppRepo
import kotlinx.coroutines.launch

class MainVM(context: Application) : AndroidViewModel(context) {
    private val repo = AppRepo()

    val preferences = context.getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE)


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
            repo.retrieveUserData().observe(viewLifecycleOwner, {
                preferences.edit().putString(NAME, it.username).apply()
                preferences.edit().putString(EMAIL, it.email).apply()
            })
        }
    }

    fun fetchFavList(viewLifecycleOwner: LifecycleOwner): MutableLiveData<MutableList<Favorite>> {
        val books: MutableLiveData<MutableList<Favorite>> = MutableLiveData<MutableList<Favorite>>()
        viewModelScope.launch {
            repo.fetchFavBook().observe(viewLifecycleOwner, {
                books.value = it
            })
        }
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

    fun updateUserName(userName: String) {
        viewModelScope.launch {
            repo.updateUserName(userName)
            repo.retrieveUserData()
        }
    }
}
