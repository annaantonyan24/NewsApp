package com.example.diplomayin.fragments.newsDetails

import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.view.View
import com.bumptech.glide.Glide
import com.example.data.model.model.room.MyNewsDataModel
import com.example.diplomayin.FragmentBaseMVVM
import com.example.diplomayin.databinding.NewsDetailsBinding
import com.example.diplomayin.utils.NewsConstants
import com.example.diplomayin.utils.viewBinding


class MyNewsDetailsFragment : FragmentBaseMVVM<NewsDetailsBinding>() {

    override val binding: NewsDetailsBinding by viewBinding()
    private var data: MyNewsDataModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle = this.arguments
        data = bundle?.getParcelable(NewsConstants.NEWS_BUNDLE)
    }

    override fun onView() {
        val content = SpannableString(data?.myNewsID.toString())
        content.setSpan(UnderlineSpan(), 0, content.length, 0)
        binding.contentLayout.visibility = View.GONE

        with(binding) {
            tvAuthor.text = data?.category
            tvContent.text = data?.content
            tvTime.text = data?.publishedAt
            tvTitle.text = data?.title
        }

        Glide.with(binding.root)
            .load(data?.image)
            .centerCrop()
            .into(binding.ivNews)
    }

}