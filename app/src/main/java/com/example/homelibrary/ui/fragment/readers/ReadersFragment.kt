package com.example.homelibrary.ui.fragment.readers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.homelibrary.`object`.Reader
import com.example.homelibrary.ui.adapter.ReadersRecyclerViewAdapter
import com.example.homelibrary.databinding.FragmentReadersBinding

class ReadersFragment : Fragment() {

    private lateinit var binding: FragmentReadersBinding
    private lateinit var recyclerViewAdapter: ReadersRecyclerViewAdapter
    private val viewModel by viewModels<ReadersViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentReadersBinding.inflate(layoutInflater, container, false)

        viewModel.getReaders()
        observeViewModel()

        return binding.root
    }

    private fun observeViewModel() {
        viewModel.readersLiveData.observe(viewLifecycleOwner, { readers ->
            viewModel.borrowingsLiveData.observe(viewLifecycleOwner, { borrowings ->
                if (readers.isNotEmpty() && borrowings.isNotEmpty()) {
                    initRecyclerViewAdapter(readers, borrowings)
                } else binding.tvEmpty.visibility = View.VISIBLE
                binding.progressBar.visibility = View.GONE
            })

        })
    }

    private fun initRecyclerViewAdapter(readers: List<Reader>, borrowings: List<String>) {
        recyclerViewAdapter = ReadersRecyclerViewAdapter(readers, borrowings)
        binding.readersRecyclerView.adapter = recyclerViewAdapter
    }
}