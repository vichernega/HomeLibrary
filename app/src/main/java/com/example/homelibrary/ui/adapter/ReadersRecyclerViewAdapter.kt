package com.example.homelibrary.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.homelibrary.R
import com.example.homelibrary.`object`.Reader
import com.example.homelibrary.databinding.ItemBaseBinding

/** ADAPTER gets the data, adapts it to recyclerView and shows to user*/
class ReadersRecyclerViewAdapter(readers: List<Reader>, borrowings: List<String>
    ): BaseRecyclerViewAdapter<Reader>(readers, borrowings) {

    /** initialize recycler item components*/
    class ReadersViewHolder(binding: ItemBaseBinding): BaseViewHolder<Reader>(binding) {
        override fun bind(item: Reader, objVal: String) {
            title.text = item.fullName
            valueTitle.setText(R.string.book_borrowings_times)
            objValue.text = objVal
        }
    }

    /** inflates the recycler item to recycler view*/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReadersViewHolder {
        return ReadersViewHolder(
            ItemBaseBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}