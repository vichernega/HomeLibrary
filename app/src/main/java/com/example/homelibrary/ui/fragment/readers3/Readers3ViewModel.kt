package com.example.homelibrary.ui.fragment.readers3

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homelibrary.model.Readers3Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Readers3ViewModel: ViewModel() {

    private val repo = Readers3Repository()
    private val _readersLiveData = repo.readersLiveData
    private val _readingsLiveData = repo.readingsLiveData
    val readersLiveData get() = _readersLiveData
    val readingsLiveData get() = _readingsLiveData

    // get list of readers that correspond to request
    fun getReaders(request: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.getReaders(request)
        }
    }
}