package com.ark.schoternews.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import com.ark.schoternews.MainActivity
import com.ark.schoternews.R
import com.ark.schoternews.data.models.Article
import com.ark.schoternews.data.repositories.NewsRepository
import com.ark.schoternews.databinding.FragmentDetailNewsBinding
import com.ark.schoternews.view_model.DetailViewModel
import com.squareup.picasso.Picasso


class DetailNewsFragment : Fragment() {
    private lateinit var binding : FragmentDetailNewsBinding
    private lateinit var viewModel : DetailViewModel
    private lateinit var article : Article

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dbLocal = (activity as MainActivity).dbLocal
        val newsRemote = (activity as MainActivity).newsRemote
        val newsRepository = NewsRepository(dbLocal, newsRemote)

        viewModel = DetailViewModel(newsRepository)

        viewModel.selectArticleData.observe(this) {
            if (it == null){
                viewModel.bookmarkAdd(article)
                Toast.makeText(requireActivity(), "Success add to bookmark", Toast.LENGTH_SHORT).show()
                return@observe
            }

            Toast.makeText(requireActivity(), "This article is already available, bookmarked", Toast.LENGTH_SHORT).show()
        }

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailNewsBinding.inflate(inflater, container, false)

        val navHostFragment = requireActivity().supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        val title = arguments?.getString("title").toString()
        val urlImage = arguments?.getString("urlImage").toString()
        val description = arguments?.getString("description").toString()
        val content = arguments?.getString("content").toString()
        val url = arguments?.getString("url").toString()
        var publishedAt = arguments?.getString("publishedAt").toString()
        val author = arguments?.getString("author").toString()

        article = Article(
            id = 0,
            author = author,
            content = content,
            description = description,
            publishedAt = publishedAt,
            urlToImage = urlImage,
            url = url,
            title = title
        )


        if (urlImage.isNotEmpty()) Picasso.get().load(urlImage).into(binding.ivImageArticle)

        publishedAt = "Published At : ${publishedAt.substring(0, 10)}"

        binding.tvPublishedAt.text = publishedAt
        binding.tvAuthor.text = author
        binding.tvDescArticle.text = description
        binding.tvTitleArticle.text = title


        binding.btnBookmark.setOnClickListener {
            viewModel.selectArticle(title)
        }

        binding.ivBackButton.setOnClickListener {
            navController.popBackStack()
        }


        return binding.root
    }

}