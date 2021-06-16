package com.example.homelibrary.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homelibrary.R
import com.example.homelibrary.`object`.Reader
import com.example.homelibrary.databinding.ItemBaseBinding

/** ADAPTER gets the data, adapts it to recyclerView and shows to user*/
class ReadersRecyclerViewAdapter(
    private val readers: List<Reader>, private val borrowings: List<String>
    ): RecyclerView.Adapter<ReadersRecyclerViewAdapter.ReadersViewHolder>() {

    class ReadersViewHolder(binding: ItemBaseBinding): RecyclerView.ViewHolder(binding.root) {
        private val fullName = binding.tvTitle
        private val borrowingsTitle = binding.tvValueTitle
        private val borrowings = binding.tvValue

        fun bind(item: Reader, borrowing: String) {
            fullName.text = item.fullName
            borrowingsTitle.setText(R.string.book_borrowings_times)
            borrowings.text = borrowing
        }
    }

    /** initialize recycler item components*/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReadersViewHolder {
        return ReadersViewHolder(
            ItemBaseBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    /** inflates the recycler item to recycler view*/
    override fun onBindViewHolder(holder: ReadersViewHolder, position: Int) {
        holder.bind(readers[position], borrowings[position])
    }

    override fun getItemCount(): Int {
        return readers.size
    }


}