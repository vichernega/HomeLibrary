package com.example.homelibrary.ui.fragment.books

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homelibrary.model.BooksRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BooksViewModel: ViewModel() {

    private  val repo = BooksRepository()
    private val _booksLiveData = repo.booksLiveData
    val booksLiveData get() = _booksLiveData

    // get books list from DB in new thread
    fun getBooks() {
        viewModelScope.launch(Dispatchers.IO) {
            repo.getBooks()
        }
    }
}