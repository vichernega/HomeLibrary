package com.example.homelibrary.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.homelibrary.`object`.remote.Book
import com.example.homelibrary.utilit.COLLECTION_BOOKS
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class BooksRepository {

    private val _booksLiveData: MutableLiveData<List<Book>> = MutableLiveData()
    val booksLiveData get() = _booksLiveData

    fun getBooks() {
        Firebase.firestore.collection(COLLECTION_BOOKS).get()
            .addOnSuccessListener {
                val books = mutableListOf<Book>()

                // convert every document to Book object and add in list
                for (documentSnapshot in it) {
                    books.add(documentSnapshot.toObject(Book::class.java))
                }
                // save list in liveData to show in fragment
                _booksLiveData.postValue(books)
                Log.d("FIRESTORE", "BooksRepo: getBooks() is SUCCESSFUL. Result: $books")
            }
            .addOnFailureListener { Log.d("FIRESTORE", "BooksRepo: getBooks() is FAILED") }
    }
}