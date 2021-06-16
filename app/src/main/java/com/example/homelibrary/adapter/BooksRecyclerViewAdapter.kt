package com.example.homelibrary.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homelibrary.R
import com.example.homelibrary.`object`.Book
import com.example.homelibrary.databinding.ItemBaseBinding

/** ADAPTER gets the data, adapts it to recyclerView and shows to user*/
class BooksRecyclerViewAdapter(private val books: List<Book>):
    RecyclerView.Adapter<BooksRecyclerViewAdapter.BooksViewHolder>() {

    /** initialize recycler item components*/
    class BooksViewHolder(binding: ItemBaseBinding): RecyclerView.ViewHolder(binding.root) {
        private val title = binding.tvTitle
        private val readingsAmountTitle = binding.tvValueTitle
        private val readingsAmount = binding.tvValue

        fun bind(item: Book) {
            title.text = item.title
            readingsAmountTitle.setText(R.string.amount_of_readings)
            readingsAmount.text = item.readingsAmount
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

    /**sets data from productList to components in recyclerView_item*/
    override fun onBindViewHolder(holder: BooksViewHolder, position: Int) {
        holder.bind(books[position])
    }

    override fun getItemCount(): Int {
        return books.size
    }


}