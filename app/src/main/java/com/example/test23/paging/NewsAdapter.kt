package com.example.test23.paging


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.test23.BR
import com.example.test23.adapters.BaseInteractionListener
import com.example.test23.data.Article
import com.example.test23.databinding.NewsItemsBinding
import javax.inject.Inject


interface NewsInteractionListener : BaseInteractionListener {
    fun onClick(item: Article)
}


class NewsAdapter @Inject constructor(

    val listener: NewsInteractionListener
) : PagingDataAdapter<Article, ItemsViewHolder>(newsComparable) {

    lateinit var binding: NewsItemsBinding
    override fun getItemViewType(position: Int): Int {
        return position
    }

    companion object {
        val newsComparable = object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(old: Article, new: Article): Boolean {
                return old.id == new.id
            }

            override fun areContentsTheSame(p0: Article, p1: Article): Boolean {
                return p0 == p1
            }

        }
    }

    override fun onBindViewHolder(holder: ItemsViewHolder, p1: Int) {

        holder.apply {
            binding.setVariable(BR.currentItem,getItem(p1))
           binding.setVariable(BR.listener, listener)
           binding.imageView.load(getItem(p1)?.urlToImage)

            }
        }


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ItemsViewHolder {

        val inflater = LayoutInflater.from(p0.context)
        binding = NewsItemsBinding.inflate(inflater, p0, false)
        return ItemsViewHolder(binding)
    }
}

class ItemsViewHolder(val binding: NewsItemsBinding) : RecyclerView.ViewHolder(binding.root) {


}


