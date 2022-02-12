package com.example.diplomayin.fragments.popularNews

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.diplomayin.databinding.FragmentNewsPopularBinding

class PopularNewsFragment : Fragment() {

    private lateinit var binding: FragmentNewsPopularBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsPopularBinding.inflate(inflater,container,false)
        return binding.root
    }

}