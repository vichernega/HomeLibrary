package com.example.homelibrary.ui.fragment.Add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homelibrary.`object`.remote.Author
import com.example.homelibrary.`object`.remote.Book
import com.example.homelibrary.`object`.remote.Reader
import com.example.homelibrary.model.AddBookRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddBookViewModel: ViewModel() {

    private val repo = AddBookRepository()
    private val _isCompleteLiveData = repo.isCompleteLiveData
    val isCompleteLiveData get() = _isCompleteLiveData

    private lateinit var readingsAmount: String
    private var authors = mutableListOf<Author>()
    private var readers = mutableListOf<Reader>()
    private var readerContacts = mutableListOf<String>()

    // save book in DB in new thread
    fun saveBook(title: String, authors: List<String>, price: String, publisher: String,
                 publishingDate: String, comment: String, shelfNumber: String){
        viewModelScope.launch(Dispatchers.IO){
            repo.saveBook(Book(title, authors, price, publisher, publishingDate, comment, shelfNumber, readingsAmount))
        }
    }

    // save authors in DB in new thread
    fun saveAuthors(){
        viewModelScope.launch(Dispatchers.IO) {
            repo.saveAuthors(authors)
            authors.clear()
        }
    }

    // save Readers in DB in new thread
    fun saveReaders(){
        viewModelScope.launch(Dispatchers.IO) {
            repo.saveReaders(readers)
            readers.clear()
        }
    }

    // add all authors in list
    fun addAuthor(fullName: String, birthDate: String, book: String){
        authors.add(Author(fullName, birthDate, listOf(book)))
    }

    // add all readers in list
    fun addReader(fullName: String, address: String, bookTitle: String, readings: String){
        readers.add(Reader(fullName, address, readerContacts, mapOf(bookTitle to readings)))

        readerContacts = mutableListOf()
    }

    // create list of reader contacts
    fun setContacts(contact1: String, contact2: String){
        readerContacts.add(contact1)
        if (contact2.isNotEmpty()) readerContacts.add(contact2)
    }

    fun setReadingsAmount(allReadings: List<String>){
        var sum = 0
        for (i in allReadings){
            sum += i.toInt()
        }
        readingsAmount = sum.toString()
    }

}