package com.example.homelibrary.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.homelibrary.R
import com.example.homelibrary.`object`.Reader
import com.example.homelibrary.databinding.ItemBaseBinding

/** ADAPTER gets the data, adapts it to recyclerView and shows to user*/
class Readers3RecyclerViewAdapter(readers: List<Reader>, readings: List<String>
    ) : BaseRecyclerViewAdapter<Reader>(readers, readings){

    /** initialize recycler item components*/
    class Readers3ViewHolder(binding: ItemBaseBinding): BaseViewHolder<Reader>(binding) {
        override fun bind(item: Reader, objVal: String) {
            title.text = item.fullName
            valueTitle.setText(R.string.readings)
            objValue.text = objVal
        }
    }

    /** inflates the recycler item to recycler view*/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Readers3ViewHolder {
        return Readers3ViewHolder(
            ItemBaseBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}