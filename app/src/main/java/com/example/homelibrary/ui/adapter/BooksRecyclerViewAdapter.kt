package com.example.homelibrary.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.homelibrary.R
import com.example.homelibrary.`object`.Book
import com.example.homelibrary.databinding.ItemBaseBinding

/** ADAPTER gets the data, adapts it to recyclerView and shows to user*/
class BooksRecyclerViewAdapter(books: List<Book>):
    BaseRecyclerViewAdapter<Book>(books, listOf()) {

    /** initialize recycler item components*/
    class BooksViewHolder(binding: ItemBaseBinding): BaseViewHolder<Book>(binding) {
        override fun bind(item: Book, objVal: String) {
            title.text = item.title
            valueTitle.setText(R.string.amount_of_readings)
            objValue.text = item.readingsAmount
        }
    }

    /** inflates the recycler item to recycler view*/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksViewHolder {
        return BooksViewHolder(
            ItemBaseBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}