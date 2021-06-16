package com.example.homelibrary.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.homelibrary.`object`.Reader
import com.example.homelibrary.utilit.COLLECTION_READERS
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Readers3Repository {

    private val _readersLiveData: MutableLiveData<List<Reader>> = MutableLiveData()
    private val _readingsLiveData: MutableLiveData<List<String>> = MutableLiveData()
    val readersLiveData get() = _readersLiveData
    val readingsLiveData get() = _readingsLiveData

    // retrieve readers from DB, filter by request, add result to lists
    fun getReaders(request: String) {
        Firebase.firestore.collection(COLLECTION_READERS)
            .get()
            .addOnSuccessListener {
                val readers = mutableListOf<Reader>()
                val readings = mutableListOf<String>()

                // convert each reader to Reader Object
                for (documentSnapShot in it) {
                    val reader = documentSnapShot.toObject(Reader::class.java)

                    // check each book if it equals request
                    for ((book, amount) in reader.books) {
                        if (book == request) {
                            readers.add(reader)
                            readings.add(amount)
                            break
                        }
                    }
                }

                // change liveData to display in fragment
                _readersLiveData.postValue(readers)
                _readingsLiveData.postValue(readings)

                Log.d("FIRESTORE", "Readers3Repo: getReaders(request) is SUCCESSFUL")
            }
            .addOnFailureListener { Log.d("FIRESTORE", "Readers3Repo: getReaders(request) is FAILED") }

    }
}