package com.example.homelibrary.ui.fragment.readers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homelibrary.model.ReadersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ReadersViewModel: ViewModel() {

    private val repo = ReadersRepository()
    private val _readersLiveData = repo.readersLiveData
    private val _borrowingsLiveData = repo.borrowingsLiveData
    val readersLiveData get() = _readersLiveData
    val borrowingsLiveData get() = _borrowingsLiveData

    // get readers from DB in new Thread
    fun getReaders() {
        viewModelScope.launch(Dispatchers.IO) {
            repo.getReaders()
        }
    }
}