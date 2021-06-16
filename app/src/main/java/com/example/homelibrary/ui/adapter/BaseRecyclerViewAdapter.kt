package com.example.homelibrary.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.homelibrary.databinding.ItemBaseBinding

abstract class BaseRecyclerViewAdapter<T>(
    private val firstList: List<T>, private val secondList: List<String>
    ): RecyclerView.Adapter<BaseRecyclerViewAdapter.BaseViewHolder<T>>(){

    abstract class BaseViewHolder<T>(binding: ItemBaseBinding): RecyclerView.ViewHolder(binding.root) {
        protected val title = binding.tvTitle
        protected val valueTitle = binding.tvValueTitle
        protected val objValue = binding.tvValue

        abstract fun bind(item: T, objVal: String)
    }

    /**sets data from productList to components in recyclerView_item*/
    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        if (secondList.isEmpty()) holder.bind(firstList[position], "")
        else holder.bind(firstList[position], secondList[position])
    }

    override fun getItemCount(): Int = firstList.size
}
