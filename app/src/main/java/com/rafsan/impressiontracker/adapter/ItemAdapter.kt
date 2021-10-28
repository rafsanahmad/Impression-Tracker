package com.rafsan.impressiontracker.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rafsan.impressiontracker.R
import com.rafsan.impressiontracker.data.ListItem
import com.rafsan.impressiontracker.databinding.RecylerviewItemBinding

class ItemAdapter(context: Context) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    private var provider: Context = context
    private lateinit var itemList: ArrayList<ListItem>

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
            binding.tvNumber.text = index.position.toString()
            if (index.isViewed) {
                binding.rowBackground.setBackgroundColor(
                    ContextCompat.getColor(
                        provider,
                        R.color.white
                    )
                )
            } else {
                binding.rowBackground.setBackgroundColor(
                    ContextCompat.getColor(
                        provider,
                        R.color.background
                    )
                )
            }
        }
    }

    fun setListData(items: ArrayList<ListItem>) {
        itemList = arrayListOf()
        this.itemList.addAll(items)
    }

    fun updateListItems(items: List<ListItem>?) {
        items?.let {
            val diffCallback = ItemDiffUtilCallback(this.itemList, it)
            val diffResult = DiffUtil.calculateDiff(diffCallback)
            this.itemList.clear()
            this.itemList.addAll(items)
            diffResult.dispatchUpdatesTo(this)
        }
    }
}