package com.tuwaiq.bookfinder.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tuwaiq.bookfinder.data.model.BooksData
import com.tuwaiq.bookfinder.data.model.VolumeInfo
import com.tuwaiq.bookfinder.data.network.BookRepo
import kotlinx.coroutines.launch

class MainVM: ViewModel() {
    private val repo = BookRepo()

fun fetchInterestingList(searchKeyWord: String? = null): LiveData<List<VolumeInfo>> {
val books = MutableLiveData<List<VolumeInfo>>()
    viewModelScope.launch {

        try {
            if (searchKeyWord.isNullOrEmpty()) {
                books.postValue(repo.fetchList())
            }else{
               books.postValue(repo.searchBooks(searchKeyWord))
            }
        }catch (e: Throwable){
            Log.e("Books search","Downloaded Books failed ${e.localizedMessage}")
        }
    }
    return books


}

}



