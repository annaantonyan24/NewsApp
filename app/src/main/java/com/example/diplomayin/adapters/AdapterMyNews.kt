package com.example.diplomayin.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.data.model.model.room.MyNewsDataModel
import com.example.diplomayin.R
import com.example.diplomayin.databinding.ItemMyNewsBinding


class AdapterMyNews(
    var itemClickCallBack: (MyNewsDataModel) -> Unit,
    var itemDeleteCallBack: (MyNewsDataModel) -> Unit,
    var itemUpdateCallBack: (MyNewsDataModel) -> Unit
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val differCallBack = object : DiffUtil.ItemCallback<MyNewsDataModel>() {

        override fun areItemsTheSame(oldItem: MyNewsDataModel, newItem: MyNewsDataModel): Boolean =
            oldItem.myNewsID == newItem.myNewsID

        override fun areContentsTheSame(
            oldItem: MyNewsDataModel,
            newItem: MyNewsDataModel
        ): Boolean = oldItem == newItem
    }

    val differ = AsyncListDiffer(this, differCallBack)

    inner class ViewHolder(private val binding: ItemMyNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(article: MyNewsDataModel, position: Int) {
            with(binding) {
                tvDescription.text = article.description
                tvCategory.text = article.category
                tvTime.text = itemView.context.getString(R.string.published, article.publishedAt)
                tvTitle.text = article.title

            }
            Glide.with(binding.root)
                .load(article.image)
                .centerCrop()
                .into(binding.ivNews)

            itemView.setOnClickListener {
                itemClickCallBack(article)
            }

            binding.btnDelete.setOnClickListener {
                itemDeleteCallBack(article)
                notifyItemRemoved(position)
            }

            binding.btnEdit.setOnClickListener {
                itemUpdateCallBack(article)
                notifyItemChanged(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return ViewHolder(
            ItemMyNewsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val article = differ.currentList[position]
        (holder as ViewHolder).bind(article, position)

    }

    override fun getItemCount(): Int = differ.currentList.size

}
