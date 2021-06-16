package com.example.homelibrary.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.homelibrary.R
import com.example.homelibrary.`object`.Author
import com.example.homelibrary.databinding.ItemBaseBinding

/** ADAPTER gets the data, adapts it to recyclerView and shows to user*/
class Authors3RecyclerViewAdapter(authorsList: List<Author>, booksAmountList: List<String>
    ): BaseRecyclerViewAdapter<Author>(authorsList, booksAmountList) {

    /** initialize recycler item components*/
    class Authors3ViewHolder(binding: ItemBaseBinding): BaseViewHolder<Author>(binding) {
        override fun bind(item: Author, objVal: String) {
            title.text = item.fullName
            valueTitle.setText(R.string.books_amount)
            objValue.text = objVal
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
}