package com.example.homelibrary.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homelibrary.`object`.Reader
import com.example.homelibrary.databinding.ItemReadersBinding

/** ADAPTER gets the data, adapts it to recyclerView and shows to user*/
class ReadersRecyclerViewAdapter(
    private val readers: List<Reader>, private val borrowings: List<String>
    ): RecyclerView.Adapter<ReadersRecyclerViewAdapter.ReadersViewHolder>() {

    class ReadersViewHolder(binding: ItemReadersBinding): RecyclerView.ViewHolder(binding.root) {
        val fullName = binding.tvFullName
        val borrowings = binding.tvBookBorrowings

        fun bind(item: Reader, borrowing: String) {
            fullName.text = item.fullName
            borrowings.text = borrowing
        }
    }

    /** initialize recycler item components*/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReadersViewHolder {
        return ReadersViewHolder(
            ItemReadersBinding.inflate(
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