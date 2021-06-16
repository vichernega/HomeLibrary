package com.example.homelibrary.ui.fragment.add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.homelibrary.databinding.FragmentAddReaderBinding

class AddReaderFragment : Fragment() {

    private lateinit var binding: FragmentAddReaderBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAddReaderBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

}