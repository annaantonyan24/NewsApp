package com.example.diplomayin.adapters

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.data.model.model.room.NewsDataModel
import com.example.diplomayin.R
import com.example.diplomayin.databinding.ItemNewsBinding
import com.example.domain.model.Data

class AdapterSearch(
    var itemClickCallBack: (NewsDataModel) -> Unit,
    var itemSaveCallBack: (NewsDataModel) -> Unit,
    var itemDeleteCallBack: (NewsDataModel) -> Unit
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val differCallBack = object : DiffUtil.ItemCallback<NewsDataModel>() {

        override fun areItemsTheSame(oldItem: NewsDataModel, newItem: NewsDataModel): Boolean =
            oldItem.url == newItem.url

        override fun areContentsTheSame(oldItem: NewsDataModel, newItem: NewsDataModel): Boolean =
            oldItem == newItem
    }

    val differ = AsyncListDiffer(this, differCallBack)

    inner class ViewHolder(private val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(article: NewsDataModel) {
            val time = article.publishedAt?.substring(startIndex = 11, endIndex = 16)
            val date: String? = article.publishedAt?.take(10)
            val publishedAt = "$time $date"

            with(binding) {
                tvAuthor.text = article.author
                tvDescription.text = article.description
                tvTime.text = itemView.context.getString(R.string.published, publishedAt)
                tvTitle.text = article.title
            }
            Glide.with(binding.root)
                .load(article.urlToImage)
                .centerCrop()
                .into(binding.ivNews)

            itemView.setOnClickListener {
                itemClickCallBack(article)
            }

            binding.btnSave.setOnClickListener {
                if (!article.isSaved) {
                    article.isSaved = true
                    binding.btnSave.setBackgroundResource(R.drawable.ic_saved)
                    itemSaveCallBack(article)
                } else {
                    article.isSaved = false
                    itemDeleteCallBack(article)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

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
        (holder as ViewHolder).bind(article)

    }

    override fun getItemCount(): Int = differ.currentList.size

}