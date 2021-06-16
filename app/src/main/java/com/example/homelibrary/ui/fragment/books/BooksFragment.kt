package com.example.homelibrary.ui.fragment.books

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.homelibrary.R
import com.example.homelibrary.`object`.remote.Book
import com.example.homelibrary.adapter.BooksRecyclerViewAdapter
import com.example.homelibrary.databinding.FragmentBooksBinding

class BooksFragment : Fragment() {

    private lateinit var binding: FragmentBooksBinding
    private lateinit var recyclerViewAdapter: BooksRecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentBooksBinding.inflate(inflater, container, false)

        observeViewModel()

        return binding.root
    }

    fun observeViewModel(){

    }

    fun initRecycler(books: List<Book>) {
        recyclerViewAdapter = BooksRecyclerViewAdapter(books)
        binding.booksRecyclerView.adapter = recyclerViewAdapter
    }


}