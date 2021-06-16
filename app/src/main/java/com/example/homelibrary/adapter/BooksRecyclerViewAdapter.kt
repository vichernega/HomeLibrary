package com.example.homelibrary.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homelibrary.`object`.remote.Book
import com.example.homelibrary.databinding.ItemBooksBinding

/** ADAPTER gets the data, adapts it to recyclerView and shows to user*/
class BooksRecyclerViewAdapter(private val books: List<Book>):
    RecyclerView.Adapter<BooksRecyclerViewAdapter.BooksViewHolder>() {

    /** initialize recycler item components*/
    class BooksViewHolder(binding: ItemBooksBinding): RecyclerView.ViewHolder(binding.root) {
        private val title = binding.tvTitle
        private val readingsAmount = binding.tvAmountOfReadings

        fun bind(item: Book) {
            title.text = item.title
            readingsAmount.text = item.readingsAmount
        }
    }

    /** inflates the recycler item to recycler view*/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksViewHolder {
        return BooksViewHolder(
            ItemBooksBinding.inflate(
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