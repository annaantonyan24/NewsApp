package com.example.diplomayin.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.diplomayin.R
import com.example.diplomayin.databinding.ItemNewsBinding
import com.example.diplomayin.databinding.ItemNewsFirstBinding
import com.example.domain.model.Data

class NewsListAdapter(
    var itemClickCallBack: (Data) -> Unit,
    var itemSaveCallBack: (Data) -> Unit
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val differCallBack = object : DiffUtil.ItemCallback<Data>() {

        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean =
            oldItem.url == newItem.url

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean =
            oldItem == newItem
    }

    val differ = AsyncListDiffer(this, differCallBack)

    inner class FirstItemViewHolder(private val binding: ItemNewsFirstBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(article: Data) {
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

            itemView.setOnClickListener {
                itemClickCallBack(article)
            }

        }

    }

    inner class ViewHolder(private val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(article: Data) {
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

            itemView.setOnClickListener {
                itemClickCallBack(article)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if (viewType == VIEW_TYPE_ONE) {
            return FirstItemViewHolder(
                ItemNewsFirstBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
        return ViewHolder(
            ItemNewsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val article = differ.currentList[position]

        if (position == VIEW_TYPE_ONE) {
            (holder as FirstItemViewHolder).bind(article)
        } else (holder as ViewHolder).bind(article)

    }

    override fun getItemCount(): Int = differ.currentList.size

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) VIEW_TYPE_ONE else VIEW_TYPE_TWO
    }

    companion object {
        const val VIEW_TYPE_ONE = 0
        const val VIEW_TYPE_TWO = 1
    }

}

