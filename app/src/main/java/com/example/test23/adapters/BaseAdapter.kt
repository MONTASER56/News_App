package com.example.test23.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

import com.example.test23.BR


abstract class BaseAdapter<T : Any>(
    private var itemList: List<T>,
    open var listener: BaseInteractionListener,
) :
    RecyclerView.Adapter<BaseAdapter.BaseViewHolder>() {
    abstract class BaseViewHolder(var binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root)

    abstract val layoutId: Int
    fun setItem(newItm: List<T>) {
        val diffResult =
            DiffUtil.calculateDiff(ArticleDiffUtil(itemList, newItm) { oldItem, newItem ->

                areItemSame(oldItem, newItem)

            })
        diffResult.dispatchUpdatesTo(this)
        itemList = newItm
    }

    fun getItems() = itemList
    open fun areItemSame(oldItem: T, newList: T): Boolean {
        return oldItem == newList
    }

    override fun getItemCount() = itemList.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return ItemsViewHolder(
            DataBindingUtil.inflate
                (LayoutInflater.from(parent.context), layoutId, parent, false)
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val currentItem = itemList[position]

        when (holder) {
            is ItemsViewHolder -> {
                holder.binding.apply {
                    setVariable(BR.currentItem, currentItem)
                    setVariable(BR.listener, listener)

                }
            }
        }
    }

    class ItemsViewHolder(binding: ViewDataBinding) : BaseViewHolder(binding)


}