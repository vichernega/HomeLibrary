package com.example.homelibrary.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homelibrary.R
import com.example.homelibrary.`object`.Reader
import com.example.homelibrary.databinding.ItemBaseBinding

/** ADAPTER gets the data, adapts it to recyclerView and shows to user*/
class Readers3RecyclerViewAdapter(
    private val readers: List<Reader>, private val readings: List<String>
    ) : RecyclerView.Adapter<Readers3RecyclerViewAdapter.Readers3ViewHolder>(){

    /** initialize recycler item components*/
    class Readers3ViewHolder(binding: ItemBaseBinding): RecyclerView.ViewHolder(binding.root) {
        private val fullName = binding.tvTitle
        private val readingsTitle = binding.tvValueTitle
        private val readings = binding.tvValue

        fun bind(item: Reader, reading: String) {
            fullName.text = item.fullName
            readingsTitle.setText(R.string.readings)
            readings.text = reading
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

    /**sets data from productList to components in recyclerView_item*/
    override fun onBindViewHolder(holder: Readers3ViewHolder, position: Int) {
        holder.bind(readers[position], readings[position])
    }

    override fun getItemCount(): Int = readers.size
}