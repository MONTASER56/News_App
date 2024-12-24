package com.example.test23.adapters

import androidx.recyclerview.widget.DiffUtil

class ArticleDiffUtil<T>(
    val oldList: List<T>, val newList: List<T>

    , val checkIfItemSame: (oldItem: T, newItem: T) -> Boolean
):DiffUtil.Callback() {
    override fun getOldListSize()= oldList.size


    override fun getNewListSize()=newList.size


    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        checkIfItemSame(oldList[oldItemPosition] , newList[newItemPosition])


    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
       return true
    }
}