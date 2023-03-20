package com.ark.schoternews.views

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ark.schoternews.MainActivity
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

        val dbLocal = (activity as MainActivity).dbLocal
        val newsRemote = (activity as MainActivity).newsRemote
        val newsRepository = NewsRepository(dbLocal, newsRemote)

        viewModel = HomeViewModel(newsRepository)

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
        val navHostFragment = requireActivity().supportFragmentManager.findFragmentById(com.ark.schoternews.R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController


        binding.recyclerNews.apply {
            adapterArticle = ArticleAdapter(navController, com.ark.schoternews.R.id.action_homeFragment_to_detailNewsFragment)
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

        binding.ivBookmarks.setOnClickListener {
            binding.progressCircular.visibility = View.VISIBLE
            viewModel.setArticleLocal()
        }

        binding.ivProfile.setOnClickListener {
            navController.navigate(com.ark.schoternews.R.id.action_homeFragment_to_profileFragment)
        }

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.setArticles("indonesia")
            binding.swipeRefresh.isRefreshing = false
        }

        binding.btnRemoveBookmark.setOnClickListener {
            val dialog = Dialog(requireContext())
            dialog.setContentView(com.ark.schoternews.R.layout.layout_dialog_confirmation)

            dialog.window?.setLayout(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )

            dialog.setCancelable(false) //Optional

            dialog.window?.attributes?.windowAnimations = com.ark.schoternews.R.style.DialogAnimation //Setting the animations to dialog

            val btnYes = dialog.findViewById<TextView>(com.ark.schoternews.R.id.btnYes)
            val btnCancel = dialog.findViewById<TextView>(com.ark.schoternews.R.id.btnCancel)

            btnCancel.setOnClickListener {
                dialog.dismiss()
            }

            btnYes.setOnClickListener {
                viewModel.removeAllBookmark()
                Toast.makeText(requireActivity(), "Success delete all data bookmark", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }

            dialog.show()
        }

        return binding.root
    }

}