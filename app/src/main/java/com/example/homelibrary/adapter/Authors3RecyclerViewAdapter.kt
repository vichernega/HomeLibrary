package com.example.homelibrary.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homelibrary.R
import com.example.homelibrary.`object`.Author
import com.example.homelibrary.databinding.ItemBaseBinding

/** ADAPTER gets the data, adapts it to recyclerView and shows to user*/
class Authors3RecyclerViewAdapter(
    private val authorsList: List<Author>, private val booksAmountList: List<String>
    ): RecyclerView.Adapter<Authors3RecyclerViewAdapter.Authors3ViewHolder>() {

    /** initialize recycler item components*/
    class Authors3ViewHolder(binding: ItemBaseBinding): RecyclerView.ViewHolder(binding.root) {
        private val fullName = binding.tvTitle
        private val booksAmountTitle = binding.tvValueTitle
        private val booksAmount = binding.tvValue

        fun bind(item: Author, amount: String) {
            fullName.text = item.fullName
            booksAmountTitle.setText(R.string.books_amount)
            booksAmount.text = amount
        }
    }

    /** inflates the recycler item to recycler view*/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Authors3ViewHolder {
        return Authors3ViewHolder(
            ItemBaseBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    /**sets data from productList to components in recyclerView_item*/
    override fun onBindViewHolder(holder: Authors3ViewHolder, position: Int) {
        holder.bind(authorsList[position], booksAmountList[position])
    }

    override fun getItemCount(): Int = authorsList.size
}