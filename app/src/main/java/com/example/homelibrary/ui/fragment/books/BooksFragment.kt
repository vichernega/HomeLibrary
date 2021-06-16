package com.example.homelibrary.ui.fragment.books

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.homelibrary.`object`.Book
import com.example.homelibrary.adapter.BooksRecyclerViewAdapter
import com.example.homelibrary.databinding.FragmentBooksBinding

class BooksFragment : Fragment() {

    private lateinit var binding: FragmentBooksBinding
    private lateinit var recyclerViewAdapter: BooksRecyclerViewAdapter
    private val viewModel by viewModels<BooksViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentBooksBinding.inflate(layoutInflater, container, false)

        viewModel.getBooks()
        observeViewModel()

        return binding.root
    }

    private fun observeViewModel(){
        viewModel.booksLiveData.observe(viewLifecycleOwner, { books ->
            if (books.isNotEmpty()) {
                initRecycler(books)
            } else binding.tvEmpty.visibility = View.VISIBLE
            binding.progressBar.visibility = View.GONE
        })
    }

    private fun initRecycler(books: List<Book>) {
        recyclerViewAdapter = BooksRecyclerViewAdapter(books)
        binding.booksRecyclerView.adapter = recyclerViewAdapter
    }


}