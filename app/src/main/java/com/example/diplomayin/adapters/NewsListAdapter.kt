package com.example.diplomayin.adapters

import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.data.model.Article
import com.example.diplomayin.R
import com.example.diplomayin.databinding.ItemNewsBinding
import com.example.diplomayin.databinding.ItemNewsFirstBinding

class NewsListAdapter : RecyclerView.Adapter<NewsListAdapter.ViewHolder>() {

    private val differCallBack = object : DiffUtil.ItemCallback<Article>() {

        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean =
            oldItem.url == newItem.url

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean =
            oldItem == newItem
    }

    val differ = AsyncListDiffer(this, differCallBack)

    inner class ViewHolder(private val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) {
            with(binding) {
                tvAuthor.text = article.author
                tvDescription.text = article.description
                tvTime.text = article.publishedAt
                tvTitle.text = article.title
            }
            Glide.with(binding.root)
                .load(article.urlToImage)
                .centerCrop()
                .into(binding.ivNews)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemNewsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.bind(article)

    }

    override fun getItemCount(): Int = differ.currentList.size

}

