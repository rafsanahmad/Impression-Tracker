package com.rafsan.impressiontracker.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rafsan.impressiontracker.databinding.RecylerviewItemBinding
import java.util.*

class ItemAdapter : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    private val itemList: List<String> = List(1000) { "$it" }
    private val viewPositions = WeakHashMap<View, Int>()

    inner class ItemViewHolder(val binding: RecylerviewItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding =
            RecylerviewItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val index = itemList[position]
        with(holder) {
            binding.tvNumber.text = index
        }
        viewPositions.put(holder.itemView, position)
    }
}