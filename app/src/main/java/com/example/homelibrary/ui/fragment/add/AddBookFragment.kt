 package com.example.homelibrary.ui.fragment.add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.homelibrary.databinding.FragmentAddBookBinding
import com.example.homelibrary.utilit.showToast

 class AddBookFragment : Fragment() {

    private lateinit var binding: FragmentAddBookBinding
    private val viewModel by viewModels<AddBookViewModel>()
    private var authorCounter = 1
    private var readerCounter = 1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAddBookBinding.inflate(layoutInflater, container, false)

        setOnClickListeners()
        observeViewModel()

        return binding.root
    }

    private fun observeViewModel() {
        viewModel.isCompleteLiveData.observe(viewLifecycleOwner, { complete ->
            if (complete) {
                showToast("Saved")
                cleanViews()
            }
        })
    }

    private fun setOnClickListeners(){
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

                viewModel.setReadingsAmount(getReadingsList())
                viewModel.saveBook(
                    binding.etTitle.text.toString().trim { it <= ' ' }, getAuthorNamesList(),
                    binding.etPrice.text.toString().trim { it <= ' ' },
                    binding.etPublisher.text.toString().trim { it <= ' ' },
                    binding.etPublishingDate.text.toString().trim { it <= ' ' },
                    binding.etComment.text.toString().trim { it <= ' ' },
                    binding.etShelfNumber.text.toString().trim { it <= ' ' }
                )

                readAuthors()       // viewModel creates list of authors
                viewModel.saveAuthors()

                readReaders()
                viewModel.saveReaders()
            }
        }
    }

    private fun getAuthorNamesList(): MutableList<String> {
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

    private fun readAuthors(){
        val bookTitle = binding.etTitle.text.toString().trim { it <= ' ' }
        viewModel.addAuthor(
            binding.etAuthorFullName1.text.toString().trim { it <= ' ' },
            binding.etAuthorBirthDate1.text.toString().trim { it <= ' ' },
            bookTitle
        )

        var fullName = binding.etAuthorFullName2.text.toString().trim { it <= ' ' }
        var birthDate = binding.etAuthorBirthDate2.text.toString().trim { it <= ' ' }
        if (fullName.isNotEmpty() && birthDate.isNotEmpty()) {
            viewModel.addAuthor(fullName, birthDate, bookTitle)
        }

        fullName = binding.etAuthorFullName3.text.toString().trim { it <= ' ' }
        birthDate = binding.etAuthorBirthDate3.text.toString().trim { it <= ' ' }
        if (fullName.isNotEmpty() && birthDate.isNotEmpty()) {
            viewModel.addAuthor(fullName, birthDate, bookTitle)
        }
    }

    private fun readReaders(){
        val bookTitle = binding.etTitle.text.toString().trim { it <= ' ' }

        // first reader
        var firstContact = binding.etReaderContact11.text.toString().trim { it <= ' ' }
        var secondContact = binding.etReaderContact12.text.toString().trim { it <= ' ' }
        var fullName = binding.etReaderFullName1.text.toString().trim { it <= ' ' }
        var address = binding.etReaderAddress1.text.toString().trim { it <= ' ' }
        var readings = binding.etReadingsAmount1.text.toString().trim { it <= ' ' }

        viewModel.setContacts(firstContact, secondContact)
        viewModel.addReader(fullName, address, bookTitle, readings)

        // second reader
        firstContact = binding.etReaderContact21.text.toString().trim { it <= ' ' }
        secondContact = binding.etReaderContact22.text.toString().trim { it <= ' ' }
        fullName = binding.etReaderFullName2.text.toString().trim { it <= ' ' }
        address = binding.etReaderAddress2.text.toString().trim { it <= ' ' }
        readings = binding.etReadingsAmount2.text.toString().trim { it <= ' ' }

        if (firstContact.isNotEmpty() && fullName.isNotEmpty() && address.isNotEmpty() && readings.isNotEmpty()) {
            viewModel.setContacts(firstContact, secondContact)
            viewModel.addReader(fullName, address, bookTitle, readings)
        }

        // third reader
        firstContact = binding.etReaderContact31.text.toString().trim { it <= ' ' }
        secondContact = binding.etReaderContact32.text.toString().trim { it <= ' ' }
        fullName = binding.etReaderFullName3.text.toString().trim { it <= ' ' }
        address = binding.etReaderAddress3.text.toString().trim { it <= ' ' }
        readings = binding.etReadingsAmount3.text.toString().trim { it <= ' ' }

        if (firstContact.isNotEmpty() && fullName.isNotEmpty() && address.isNotEmpty() && readings.isNotEmpty()) {
            viewModel.setContacts(firstContact, secondContact)
            viewModel.addReader(fullName, address, bookTitle, readings)
        }

        // fourth reader
        firstContact = binding.etReaderContact41.text.toString().trim { it <= ' ' }
        secondContact = binding.etReaderContact42.text.toString().trim { it <= ' ' }
        fullName = binding.etReaderFullName4.text.toString().trim { it <= ' ' }
        address = binding.etReaderAddress4.text.toString().trim { it <= ' ' }
        readings = binding.etReadingsAmount4.text.toString().trim { it <= ' ' }

        if (firstContact.isNotEmpty() && fullName.isNotEmpty() && address.isNotEmpty() && readings.isNotEmpty()) {
            viewModel.setContacts(firstContact, secondContact)
            viewModel.addReader(fullName, address, bookTitle, readings)
        }

        // fifth reader
        firstContact = binding.etReaderContact51.text.toString().trim { it <= ' ' }
        secondContact = binding.etReaderContact52.text.toString().trim { it <= ' ' }
        fullName = binding.etReaderFullName5.text.toString().trim { it <= ' ' }
        address = binding.etReaderAddress5.text.toString().trim { it <= ' ' }
        readings = binding.etReadingsAmount5.text.toString().trim { it <= ' ' }

        if (firstContact.isNotEmpty() && fullName.isNotEmpty() && address.isNotEmpty() && readings.isNotEmpty()) {
            viewModel.setContacts(firstContact, secondContact)
            viewModel.addReader(fullName, address, bookTitle, readings)
        }

    }

    private fun getReadingsList(): MutableList<String> {
        val readings = mutableListOf<String>()

        readings.add(binding.etReadingsAmount1.text.toString().trim { it <= ' ' })
        if (binding.etReadingsAmount2.text.toString().trim { it <= ' ' }.isNotEmpty()) {
            readings.add(binding.etReadingsAmount2.text.toString().trim { it <= ' ' })
        }
        if (binding.etReadingsAmount3.text.toString().trim { it <= ' ' }.isNotEmpty()) {
            readings.add(binding.etReadingsAmount3.text.toString().trim { it <= ' ' })
        }
        if (binding.etReadingsAmount4.text.toString().trim { it <= ' ' }.isNotEmpty()) {
            readings.add(binding.etReadingsAmount4.text.toString().trim { it <= ' ' })
        }
        if (binding.etReadingsAmount5.text.toString().trim { it <= ' ' }.isNotEmpty()) {
            readings.add(binding.etReadingsAmount5.text.toString().trim { it <= ' ' })
        }

        return readings
    }

    private fun checkEditTexts(): Boolean {           // returns true if user info is correct
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
        if (binding.etReadingsAmount1.text.toString().trim { it <= ' ' }.isEmpty()){
            binding.tilReadingsAmount1.error = "Fill in!"
            result = false
        }

        return result
    }

    private fun cleanViews(){
        binding.etTitle.setText("")                     // book information
        binding.etPublisher.setText("")
        binding.etPublishingDate.setText("")
        binding.etPrice.setText("")
        binding.etComment.setText("")
        binding.etShelfNumber.setText("")

        binding.etAuthorFullName1.setText("")           // author 1
        binding.etAuthorBirthDate1.setText("")
        binding.etAuthorFullName2.setText("")           // author 2
        binding.etAuthorBirthDate2.setText("")
        binding.etAuthorFullName3.setText("")           // author 3
        binding.etAuthorBirthDate3.setText("")

        binding.etReaderFullName1.setText("")           // reader 1
        binding.etReaderAddress1.setText("")
        binding.etReaderContact11.setText("")
        binding.etReaderContact12.setText("")
        binding.etReadingsAmount1.setText("")

        binding.etReaderFullName2.setText("")           // reader 2
        binding.etReaderAddress2.setText("")
        binding.etReaderContact21.setText("")
        binding.etReaderContact22.setText("")
        binding.etReadingsAmount2.setText("")

        binding.etReaderFullName3.setText("")           // reader 3
        binding.etReaderAddress3.setText("")
        binding.etReaderContact31.setText("")
        binding.etReaderContact32.setText("")
        binding.etReadingsAmount3.setText("")

        binding.etReaderFullName4.setText("")           // reader 4
        binding.etReaderAddress4.setText("")
        binding.etReaderContact41.setText("")
        binding.etReaderContact42.setText("")
        binding.etReadingsAmount4.setText("")

        binding.etReaderFullName5.setText("")           // reader 5
        binding.etReaderAddress5.setText("")
        binding.etReaderContact51.setText("")
        binding.etReaderContact52.setText("")
        binding.etReadingsAmount5.setText("")
    }

}