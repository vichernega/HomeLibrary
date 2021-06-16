package com.example.homelibrary.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.homelibrary.`object`.Author
import com.example.homelibrary.utilit.COLLECTION_AUTHORS
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Authors3Repository {

    private val _authorsLivedata: MutableLiveData<List<Author>> = MutableLiveData()
    private val _booksAmountLivedata: MutableLiveData<List<String>> = MutableLiveData()
    val authorsLivedata get() = _authorsLivedata
    val booksAmountLiveData get() = _booksAmountLivedata

    // retrieve authors from DB
    fun getAuthors() {
        Firebase.firestore.collection(COLLECTION_AUTHORS)
            .get()
            .addOnSuccessListener {
                val authors = mutableListOf<Author>()
                val booksAmount = mutableListOf<String>()

                // convert each document to Author object, count booksAmount, add result to lists
                for (documentSnapshot in it) {
                    val author = documentSnapshot.toObject(Author::class.java)
                    val amount = countBooksAmount(author)
                    if (amount >= 3) {
                        authors.add(author)
                        booksAmount.add(amount.toString())
                    }
                }

                // change liveData to show in fragment
                _authorsLivedata.postValue(authors)
                _booksAmountLivedata.postValue(booksAmount)

                Log.d("FIRESTORE", "Author3Repo: getAuthors() is SUCCESSFUL.")
            }
            .addOnFailureListener { Log.d("FIRESTORE", "Author3Repo: getAuthors() is FAILED") }
    }

    private fun countBooksAmount(author: Author): Int {
        var total = 0
        for (book in author.books) {
            total++
        }
        return total
    }
}