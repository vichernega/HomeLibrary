package com.example.homelibrary.ui.fragment.readers3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.homelibrary.R
import com.example.homelibrary.`object`.Reader
import com.example.homelibrary.adapter.Readers3RecyclerViewAdapter
import com.example.homelibrary.databinding.FragmentReaders3Binding
import com.example.homelibrary.utilit.showToast

class Readers3Fragment : Fragment() {

    private lateinit var binding: FragmentReaders3Binding
    private lateinit var recyclerViewAdapter: Readers3RecyclerViewAdapter
    private val viewModel by viewModels<Readers3ViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentReaders3Binding.inflate(layoutInflater, container, false)

        setClickListeners()
        observeViewModel()

        return binding.root
    }

    private fun setClickListeners() {

        // click on button search
        binding.btnSearch.setOnClickListener {
            val request = binding.etSearch.text.toString()
            if (request.trim { it <= ' ' }.isNotEmpty()) {
                binding.progressBar.visibility = View.VISIBLE
                viewModel.getReaders(request)
            } else showToast("Input book title")
        }
    }

    private fun observeViewModel() {
        viewModel.readersLiveData.observe(viewLifecycleOwner, { readers ->
            viewModel.readingsLiveData.observe(viewLifecycleOwner, { readings ->
                if (readers.isNotEmpty() && readings.isNotEmpty()) {
                    initRecyclerViewAdapter(readers, readings)
                } else {
                    binding.tvEmpty.visibility = View.VISIBLE
                    initRecyclerViewAdapter(listOf(), listOf())     // clear adapter
                }
                binding.progressBar.visibility = View.GONE
            })
        })
    }

    private fun initRecyclerViewAdapter(readers: List<Reader>, readings: List<String>) {
        recyclerViewAdapter = Readers3RecyclerViewAdapter(readers, readings)
        binding.readers3RecyclerView.adapter = recyclerViewAdapter
    }

}