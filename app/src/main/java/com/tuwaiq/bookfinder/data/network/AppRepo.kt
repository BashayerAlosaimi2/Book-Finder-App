package com.tuwaiq.bookfinder.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.*
import com.google.firebase.firestore.auth.User
import com.google.firebase.firestore.ktx.getField
import com.tuwaiq.bookfinder.data.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class AppRepo {

    //val user: Users
   // var userRetrivedData = Users("", "")
    private var db = FirebaseFirestore.getInstance()
    val uid = FirebaseAuth.getInstance().currentUser?.uid
    private val api1 = BookBuilder.bookAPI

    // ------- functions with with api -------
    suspend fun fetchList(): List<VolumeInfo> = withContext(Dispatchers.IO) {
        api1.fetchBooks().items
    }

    suspend fun searchBooks(searchKeyWord: String): List<VolumeInfo> = withContext(Dispatchers.IO) {
        api1.searchBook(searchKeyWord).items
    }
  

    // ------- functions with firebase -------
    suspend fun fetchFavBook(): MutableLiveData<MutableList<Favorite>> {

        var favList: MutableLiveData<MutableList<Favorite>> = MutableLiveData()
        withContext(Dispatchers.IO) {
            var favoriteListIn = mutableListOf<Favorite>()
            withContext(Dispatchers.IO) {

                db.collection("Users").document("$uid").collection("Favorite").get()
                    .addOnCompleteListener() {
                        it.addOnSuccessListener { snapshot ->
                            snapshot?.let { docSnap ->
                                var documents = docSnap.documents

                                documents.forEach { documents ->
                                    var FavObj = documents.toObject(Favorite::class.java)
                                    FavObj?.let {
                                        //favList.add(it)
                                        Log.d("books favObj", FavObj.toString())
                                        favoriteListIn.add(FavObj)
                                        Log.d("books favoriteListIn", favoriteListIn.toString())
                                    }
                                }
                                favList.value = favoriteListIn
                                Log.d("books favList", favList.toString())
                            }
                        }
                    }
            }//.await()
        }
        // return favoriteListIn
        return favList
    }


    suspend fun deleteFavBooks(favBookId: String) = withContext(Dispatchers.IO) {
        db.collection("Users").document("$uid").collection("Favorite").document(favBookId)
            .delete()
            .addOnSuccessListener {
                Log.d("TAG", "Book successfully Deleted !")
            }
            .addOnFailureListener { e ->
                Log.w("TAG", "Error Deleting The Book", e)
            }
    }

    suspend fun saveUserData(user: Users) = withContext(Dispatchers.IO) {
        db.collection("Users").document("$uid").set(user)
    }

    suspend fun saveBookToFavorite(favBook: Favorite) = withContext(Dispatchers.IO) {
        db.collection("Users").document("$uid").collection("Favorite").document(favBook.id)
            .set(favBook)
            .addOnSuccessListener {
                Log.d("TAG", "DocumentSnapshot successfully written!")
            }
            .addOnFailureListener { e ->
                Log.w("TAG", "Error writing document", e)
            }

    }

    suspend fun retrieveUserData(): MutableLiveData<String> {
        var userName = MutableLiveData<String>()
        withContext(Dispatchers.IO) {
            db.collection("Users").document("$uid").get().addOnCompleteListener() {
                it.addOnSuccessListener { snapshot ->
                    snapshot?.let { docSnap ->
                        val user = docSnap.toObject(Users::class.java)
                        user?.let {
                            userName.postValue(user.username)
                        }

                    }
                }
            }
        }
        return userName
    }



    suspend fun updateUserName(Name: String) =
        withContext(Dispatchers.IO) {
            db.collection("Users").document("$uid").update("username", Name)

        }
    }

