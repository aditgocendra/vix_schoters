package com.ark.schoternews.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ark.schoternews.databinding.FragmentDetailNewsBinding
import com.squareup.picasso.Picasso


class DetailNewsFragment : Fragment() {
    private lateinit var binding : FragmentDetailNewsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailNewsBinding.inflate(inflater, container, false)

        val title = arguments?.getString("title").toString()
        val urlImage = arguments?.getString("urlImage").toString()
        val author = arguments?.getString("author").toString()
        val publishedAt = arguments?.getString("publishedAt").toString()
        val description = arguments?.getString("description").toString()


        if (urlImage.isNotEmpty()) Picasso.get().load(urlImage).into(binding.ivImageArticle)

        binding.tvPublishedAt.text = publishedAt
        binding.tvAuthor.text = author
        binding.tvDescArticle.text = description
        binding.tvTitleArticle.text = title

        return binding.root
    }


}