package com.example.diplomayin.fragments.newsDetails

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import com.bumptech.glide.Glide
import com.example.diplomayin.FragmentBaseMVVM
import com.example.diplomayin.databinding.NewsDetailsBinding
import com.example.diplomayin.utils.NewsConstants
import com.example.diplomayin.utils.viewBinding
import com.example.domain.model.Data


class NewsDetailsFragment : FragmentBaseMVVM<NewsDetailsBinding>() {

    override val binding: NewsDetailsBinding by viewBinding()
    private var data: Data? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle = this.arguments
        data = bundle?.getParcelable(NewsConstants.NEWS_BUNDLE)
    }

    override fun onView() {
        val content = SpannableString(data?.url)
        content.setSpan(UnderlineSpan(), 0, content.length, 0)
        val uri: Uri = Uri.parse(data?.url)
        val intent = Intent(Intent.ACTION_VIEW, uri)

        with(binding){
            tvAuthor.text = data?.author
            tvContent.text = data?.content
            tvTime.text = data?.publishedAt
            tvTitle.text = data?.title
            tvUrl.text = content
        }

        Glide.with(binding.root)
            .load(data?.urlToImage)
            .centerCrop()
            .into(binding.ivNews)

        binding.tvUrl.setOnClickListener {
            startActivity(intent)
        }
    }

}