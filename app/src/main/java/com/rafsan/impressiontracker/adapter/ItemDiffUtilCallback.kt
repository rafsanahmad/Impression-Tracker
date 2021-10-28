package com.rafsan.impressiontracker.adapter

import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil
import com.rafsan.impressiontracker.data.ListItem

class ItemDiffUtilCallback(oldList: List<ListItem>, newList: List<ListItem>) : DiffUtil.Callback() {

    private val mOldList: List<ListItem>
    private val mNewList: List<ListItem>


    init {
        mOldList = oldList
        mNewList = newList
    }

    override fun getOldListSize(): Int {
        return mOldList.size
    }

    override fun getNewListSize(): Int {
        return mNewList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldList[oldItemPosition].position == mNewList[newItemPosition].position
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldList[oldItemPosition] == mNewList[newItemPosition]
    }

    @Nullable
    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        // Implement method if you're going to use ItemAnimator
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }
}