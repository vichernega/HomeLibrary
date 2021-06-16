package com.example.homelibrary.ui.fragment.authors3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.homelibrary.`object`.Author
import com.example.homelibrary.ui.adapter.Authors3RecyclerViewAdapter
import com.example.homelibrary.databinding.FragmentAuthors3Binding

class Authors3Fragment : Fragment() {

    private lateinit var binding: FragmentAuthors3Binding
    private lateinit var recyclerViewAdapter: Authors3RecyclerViewAdapter
    private val viewModel by viewModels<Authors3ViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAuthors3Binding.inflate(layoutInflater, container, false)

        viewModel.getAuthors()
        observeViewModel()

        return binding.root
    }

    private fun observeViewModel(){
        viewModel.authorsLiveData.observe(viewLifecycleOwner, { authors ->
            viewModel.booksAmountLiveData.observe(viewLifecycleOwner, { booksAmount ->
                if (authors.isNotEmpty() && booksAmount.isNotEmpty()) {
                    initRecyclerViewAdapter(authors, booksAmount)
                } else binding.tvEmpty.visibility = View.VISIBLE
                binding.progressBar.visibility = View.GONE
            })
        })
    }

    private fun initRecyclerViewAdapter(authorsList: List<Author>, booksAmountList: List<String>) {
        recyclerViewAdapter = Authors3RecyclerViewAdapter(authorsList, booksAmountList)
        binding.authors3RecyclerView.adapter = recyclerViewAdapter
    }

}