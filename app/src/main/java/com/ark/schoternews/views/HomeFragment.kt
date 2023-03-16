package com.ark.schoternews.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ark.schoternews.R
import com.ark.schoternews.core.adapter.ArticleAdapter
import com.ark.schoternews.data.repositories.NewsRepository
import com.ark.schoternews.databinding.FragmentHomeBinding
import com.ark.schoternews.view_model.HomeViewModel


class HomeFragment : Fragment() {
    private lateinit var binding : FragmentHomeBinding
    private lateinit var adapterArticle : ArticleAdapter
    private lateinit var viewModel : HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = HomeViewModel(NewsRepository())

        viewModel.listArticle.observe(this) {
            binding.progressCircular.visibility = View.GONE

            adapterArticle.articles = it

            if (it.isEmpty()){
                binding.tvErrorMessage.visibility = View.VISIBLE

            }else{
                binding.tvErrorMessage.visibility = View.GONE
            }

        }

    }

    override fun onResume() {
        super.onResume()

        // This default set article data
        // Default query = indonesia
        if (adapterArticle.articles.isEmpty()){
            binding.progressCircular.visibility = View.VISIBLE
            viewModel.setArticles("indonesia")
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val navHostFragment = requireActivity().supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController


        binding.recyclerNews.apply {
            adapterArticle = ArticleAdapter(navController, R.id.action_homeFragment_to_detailNewsFragment)
            adapter = adapterArticle
            layoutManager = LinearLayoutManager(activity)
        }

        binding.btnSearch.setOnClickListener {

            val query = binding.edtSearch.text

            if (query.isEmpty()) return@setOnClickListener

            viewModel.setArticles(query.toString())
            binding.tvErrorMessage.visibility = View.GONE
            binding.progressCircular.visibility = View.VISIBLE


        }

        return binding.root
    }

}