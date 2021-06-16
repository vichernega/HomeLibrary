package com.example.homelibrary.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.homelibrary.`object`.remote.Author
import com.example.homelibrary.`object`.remote.Book
import com.example.homelibrary.`object`.remote.Reader
import com.example.homelibrary.utilit.COLLECTION_AUTHORS
import com.example.homelibrary.utilit.COLLECTION_BOOKS
import com.example.homelibrary.utilit.COLLECTION_READERS
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AddBookRepository {

    private val _isCompleteLiveData: MutableLiveData<Boolean> = MutableLiveData()   // live data book saving complete
    val isCompleteLiveData: MutableLiveData<Boolean> get() = _isCompleteLiveData

    fun saveBook(book: Book){
        Firebase.firestore.collection(COLLECTION_BOOKS).document(book.title)
            .set(book)
            .addOnSuccessListener {
                _isCompleteLiveData.postValue(true)
                Log.d("FIRESTORE", "AddBookRepository: saveBook() is SUCCESSFUL")
            }
            .addOnFailureListener { Log.d("FIRESTORE", "AddBookRepository: saveBook() is FAILED") }
    }

    fun saveAuthors(authors: List<Author>){
        val path = Firebase.firestore.collection(COLLECTION_AUTHORS)
        for (author in authors) {

            // check if author is already in DB
            path.document(author.fullName).get()
                .addOnSuccessListener {

                    // if document exists update field "books" in DB
                    if (it.exists()){
                        val remoteReader = it.toObject(Author::class.java)
                        val books = remoteReader?.books as MutableList<String>
                        books.add(author.books[0])

                        path.document(author.fullName).update("books", books)
                            .addOnSuccessListener {
                                Log.d("FIRESTORE", "AddBookRepo: ${author.fullName} saveAuthors() book is SUCCESSFUL")
                            }
                            .addOnFailureListener { Log.d("FIRESTORE", "AddBookRepository: ${author.fullName} saveAuthors() book is FAILED") }
                    }

                    // if author is not in DB - add him
                    else {
                        path.document(author.fullName).set(author)
                            .addOnSuccessListener {
                                Log.d("FIRESTORE", "AddBookRepoy: ${author.fullName} saveAuthors() is SUCCESSFUL")
                            }
                            .addOnFailureListener { Log.d("FIRESTORE", "AddBookRepo: ${author.fullName} saveAuthors() is FAILED") }
                    }
                }
                .addOnFailureListener { Log.d("FIRESTORE", "AddBookRepo: saveAuthors() get is FAILED") }
        }
    }

    fun saveReaders(readers: List<Reader>) {
        val path = Firebase.firestore.collection(COLLECTION_READERS)
        for (reader in readers) {

            // check if reader is already in DB
            path.document(reader.fullName).get()
                .addOnSuccessListener {

                    // if document exists update field "books" in DB
                    if (it.exists()) {
                        val updateBooks = it.toObject(Reader::class.java)?.books as MutableMap<String, String>
                        updateBooks[reader.books.keys.first()] = reader.books.values.first()

                        path.document(reader.fullName).update("books", updateBooks)
                            .addOnSuccessListener {
                                Log.d("FIRESTORE", "AddBookRepository: ${reader.fullName} saveReaders() book is SUCCESSFUL")
                            }
                            .addOnFailureListener { Log.d("FIRESTORE", "AddBookRepository: ${reader.fullName} saveReaders() book is FAILED") }
                    }

                    // if reader is not in DB - create him
                    else {
                        path.document(reader.fullName)
                            .set(reader)
                            .addOnSuccessListener {
                                Log.d("FIRESTORE", "AddBookRepository: ${reader.fullName} saveReaders() is SUCCESSFUL")
                            }
                            .addOnFailureListener { Log.d("FIRESTORE", "AddBookRepository: ${reader.fullName} saveReaders() is FAILED") }
                    }
                }
                .addOnFailureListener { Log.d("FIRESTORE", "AddBookRepo: saveReaders() get is FAILED") }

        }
    }
}