package com.example.homelibrary.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.homelibrary.`object`.Reader
import com.example.homelibrary.utilit.COLLECTION_READERS
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ReadersRepository {

    private val _readersLiveData: MutableLiveData<List<Reader>> = MutableLiveData()
    private val _borrowingsLiveData: MutableLiveData<List<String>> = MutableLiveData()
    val readersLiveData get() = _readersLiveData
    val borrowingsLiveData get() = _borrowingsLiveData

    // get and convert readers from DB to show in view
    fun getReaders() {
        Firebase.firestore.collection(COLLECTION_READERS)
            .get()
            .addOnSuccessListener {
                val readers = mutableListOf<Reader>()
                val borrowings = mutableListOf<String>()

                // convert every document, count borrowings and add result to lists
                for (documentSnapshot in it) {
                    val reader = documentSnapshot.toObject(Reader::class.java)
                    readers.add(reader)
                    borrowings.add(countBorrowings(reader))
                }

                // changing liveData to show in fragment
                _readersLiveData.postValue(readers)
                _borrowingsLiveData.postValue(borrowings)
                Log.d("FIRESTORE", "ReadersRepo: getReaders() is SUCCESSFUL. Result: $readers and borrowings")
            }
            .addOnFailureListener { Log.d("FIRESTORE", "ReadersRepo: getReaders() is FAILED") }
    }

    // count data from DB
    private fun countBorrowings(reader: Reader): String {
        var total = 0
        for ((_, times) in reader.books) {
            total += times.toInt()
        }
        return total.toString()
    }
}