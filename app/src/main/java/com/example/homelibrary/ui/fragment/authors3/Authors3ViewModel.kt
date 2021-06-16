package com.example.homelibrary.ui.fragment.authors3

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homelibrary.model.Authors3Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Authors3ViewModel: ViewModel() {

    private val repo = Authors3Repository()
    private val _authorsLiveData = repo.authorsLivedata
    private val _booksAmountLiveData = repo.booksAmountLiveData

    val authorsLiveData get() = _authorsLiveData
    val booksAmountLiveData get() = _booksAmountLiveData

    // retrieve authors in new Thread
    fun getAuthors() {
        viewModelScope.launch(Dispatchers.IO) {
            repo.getAuthors()
        }
    }
}