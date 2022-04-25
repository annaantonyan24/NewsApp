package com.example.diplomayin.adapters

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.data.model.model.room.NewsDataModel
import com.example.diplomayin.R
import com.example.diplomayin.databinding.ItemNewsBinding
import com.example.diplomayin.databinding.ItemNewsFirstBinding
import com.example.domain.model.Data

class NewsListAdapter(
    var itemClickCallBack: (NewsDataModel) -> Unit,
    var itemSaveCallBack: (NewsDataModel, isAddedLibrary: Boolean) -> Unit,
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

    inner class FirstItemViewHolder(private val binding: ItemNewsFirstBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SimpleDateFormat")
        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(article: NewsDataModel) {
            val time = article.publishedAt?.substring(startIndex = 11, endIndex = 16)
            val date: String? = article.publishedAt?.take(10)
            val publishedAt = "$time $date"

            with(binding) {
                tvAuthor.text = article.author
                tvDescription.text = article.description
                tvTime.text = itemView.context.getString(R.string.published_first, publishedAt)
                tvTitle.text = article.title
                if(article.isSaved) {
                    btnSave.setBackgroundResource(R.drawable.ic_saved)
                } else {
                    btnSave.setBackgroundResource(R.drawable.ic_not_saved)
                }
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
                    itemSaveCallBack(article, article.isSaved)
                } else {
                    article.isSaved = false
                    binding.btnSave.setBackgroundResource(R.drawable.ic_not_saved)
                    itemDeleteCallBack(article)
                }
            }

        }

    }

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
                if(article.isSaved) {
                    btnSave.setBackgroundResource(R.drawable.ic_saved)
                } else {
                    btnSave.setBackgroundResource(R.drawable.ic_not_saved)
                }
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
                    itemSaveCallBack(article, article.isSaved)
                } else {
                    article.isSaved = false
                    binding.btnSave.setBackgroundResource(R.drawable.ic_not_saved)
                    itemDeleteCallBack(article)
                }
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

    @RequiresApi(Build.VERSION_CODES.O)
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

