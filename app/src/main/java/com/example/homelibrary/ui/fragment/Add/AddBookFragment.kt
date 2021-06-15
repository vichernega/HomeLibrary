package com.example.homelibrary.ui.fragment.Add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.homelibrary.databinding.FragmentAddBookBinding

class AddBookFragment : Fragment() {

    private lateinit var binding: FragmentAddBookBinding
    private val viewModel by viewModels<AddBookViewModel>()
    private var authorCounter = 1
    private var readerCounter = 1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAddBookBinding.inflate(layoutInflater, container, false)

        setOnClickListeners()

        return binding.root
    }



    fun setOnClickListeners(){
        // add authors
        binding.btnAddAuthor.setOnClickListener {
            authorCounter++
            when (authorCounter){
                2 -> binding.addAuthorContainer2.visibility = View.VISIBLE
                3 -> {
                    binding.addAuthorContainer3.visibility = View.VISIBLE
                    it.visibility = View.GONE
                }
            }
        }

        // add readers
        binding.btnAddReader.setOnClickListener {
            readerCounter++
            when (readerCounter){
                2 -> binding.addReaderContainer2.visibility = View.VISIBLE
                3 -> binding.addReaderContainer3.visibility = View.VISIBLE
                4 -> binding.addReaderContainer4.visibility = View.VISIBLE
                5 -> {
                    binding.addReaderContainer5.visibility = View.VISIBLE
                    it.visibility = View.GONE
                }
            }
        }

        // add contact fields
        binding.btnAddReaderContact1.setOnClickListener {
            binding.tilReaderContact12.visibility = View.VISIBLE
            it.visibility = View.GONE
        }
        binding.btnAddReaderContact2.setOnClickListener {
            binding.tilReaderContact22.visibility = View.VISIBLE
            it.visibility = View.GONE
        }
        binding.btnAddReaderContact3.setOnClickListener {
            binding.tilReaderContact32.visibility = View.VISIBLE
            it.visibility = View.GONE
        }
        binding.btnAddReaderContact4.setOnClickListener {
            binding.tilReaderContact42.visibility = View.VISIBLE
            it.visibility = View.GONE
        }
        binding.btnAddReaderContact5.setOnClickListener {
            binding.tilReaderContact52.visibility = View.VISIBLE
            it.visibility = View.GONE
        }

        // save book
        binding.btnConfirm.setOnClickListener {
            if (checkEditTexts()){
                // saveBook()
            }
        }
    }

    fun getAuthorsList(): MutableList<String> {
        val authors = mutableListOf<String>()

        authors.add(binding.etAuthorFullName1.text.toString().trim { it <= ' ' })
        if (binding.etAuthorFullName2.text.toString().trim { it <= ' ' }.isNotEmpty()){
            authors.add(binding.etAuthorFullName2.text.toString().trim { it <= ' ' })
        }
        if (binding.etAuthorFullName3.text.toString().trim { it <= ' ' }.isNotEmpty()){
            authors.add(binding.etAuthorFullName3.text.toString().trim { it <= ' ' })
        }

        return authors
    }

    fun getReadingsList(): MutableList<String> {
        val readings = mutableListOf<String>()

        readings.add(binding.etReadingsCount1.text.toString().trim { it <= ' ' })
        if (binding.etReadingsCount2.text.toString().trim { it <= ' ' }.isNotEmpty()) {
            readings.add(binding.etReadingsCount2.text.toString().trim { it <= ' ' })
        }
        if (binding.etReadingsCount3.text.toString().trim { it <= ' ' }.isNotEmpty()) {
            readings.add(binding.etReadingsCount3.text.toString().trim { it <= ' ' })
        }
        if (binding.etReadingsCount4.text.toString().trim { it <= ' ' }.isNotEmpty()) {
            readings.add(binding.etReadingsCount4.text.toString().trim { it <= ' ' })
        }
        if (binding.etReadingsCount5.text.toString().trim { it <= ' ' }.isNotEmpty()) {
            readings.add(binding.etReadingsCount5.text.toString().trim { it <= ' ' })
        }

        return readings
    }

    fun checkEditTexts(): Boolean {           // returns true if user info is correct
        var result = true
        val datePattern = "\\p{Digit}{2}/\\p{Digit}{2}/\\p{Digit}{4}".toRegex()

        // title
        if (binding.etTitle.text.toString().trim { it <= ' ' }.isEmpty()){
            binding.tilTitle.error = "Fill in!"
            result = false
        }

        // publisher
        if (binding.etPublisher.text.toString().trim { it <= ' ' }.isEmpty()){
            binding.tilPublisher.error = "Fill in!"
            result = false
        }

        // publishing date
        val publishingDate = binding.etPublishingDate.text.toString().trim { it <= ' ' }

        if (publishingDate.isEmpty()){
            binding.tilPublishingDate.error = "Fill in!"
            result = false
        } else if (publishingDate.length != 10 || !publishingDate.matches(datePattern)){
            binding.tilPublishingDate.error = "Format DD/MM/YYYY"
            result = false
        }

        // price
        if (binding.etPrice.text.toString().trim { it <= ' ' }.isEmpty()){
            binding.tilPrice.error = "Fill in!"
            result = false
        }

        // comment
        if (binding.etComment.text.toString().trim { it <= ' ' }.isEmpty()){
            binding.tilComment.error = "Fill in!"
            result = false
        }

        // shelf number
        if (binding.etShelfNumber.text.toString().trim { it <= ' ' }.isEmpty()){
            binding.tilShelfNumber.error = "Fill in!"
            result = false
        }

        /**AUTHORS CHECK*/

        // AUTHOR 1
        // full name
        if (binding.etAuthorFullName1.text.toString().trim { it <= ' ' }.isEmpty()){
            binding.tilAuthorFullName1.error = "Fill in!"
            result = false
        }
        // date of birth
        val authorBirthDate1 = binding.etAuthorBirthDate1.text.toString().trim { it <= ' ' }

        if (authorBirthDate1.isEmpty()){
            binding.tilAuthorBirthDate1.error = "Fill in!"
            result = false
        } else if (!authorBirthDate1.matches(datePattern)){
            binding.tilAuthorBirthDate1.error = "Format DD/MM/YYYY"
            result = false
        }

        // AUTHOR 2
        // date of birth
        val authorBirthDate2 = binding.etAuthorBirthDate2.text.toString().trim { it <= ' ' }

        if (binding.addAuthorContainer2.visibility == View.VISIBLE && authorBirthDate2.isNotEmpty()) {
            if (!authorBirthDate2.matches(datePattern)){
                binding.tilAuthorBirthDate2.error = "Format DD/MM/YYYY"
                result = false
            }
        }

        // AUTHOR 3
        // date of birth
        val authorBirthDate3 = binding.etAuthorBirthDate3.text.toString().trim { it <= ' ' }

        if (binding.addAuthorContainer3.visibility == View.VISIBLE && authorBirthDate3.isNotEmpty()){
            if (!authorBirthDate3.matches(datePattern)){
                binding.tilAuthorBirthDate3.error = "Format DD/MM/YYYY"
                result = false
            }
        }

        /**READERS CHECK*/

        // READER 1
        // full name
        if (binding.etReaderFullName1.text.toString().trim { it <= ' ' }.isEmpty()){
            binding.tilReaderFullName1.error = "Fill in!"
            result = false
        }
        // address
        if (binding.etReaderAddress1.text.toString().trim { it <= ' ' }.isEmpty()){
            binding.tilReaderAddress1.error = "Fill in!"
            result = false
        }
        // first contact
        if (binding.etReaderContact11.text.toString().trim { it <= ' ' }.isEmpty()){
            binding.tilReaderContact11.error = "Fill in!"
            result = false
        }
        // count of readings
        if (binding.etReadingsCount1.text.toString().trim { it <= ' ' }.isEmpty()){
            binding.tilReadingsCount1.error = "Fill in!"
            result = false
        }

        return result
    }

}