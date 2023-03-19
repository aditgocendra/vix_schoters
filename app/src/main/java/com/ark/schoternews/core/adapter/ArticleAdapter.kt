package com.ark.schoternews.core.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ark.schoternews.data.models.Article
import com.ark.schoternews.databinding.ItemNewsBinding
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*

class ArticleAdapter(private val navController : NavController, private val action : Int) : RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {

    inner class ArticleViewHolder( val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<Article>(){
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.publishedAt == newItem.publishedAt
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

    }

    private val differ = AsyncListDiffer(this, diffCallback)

    var articles : List<Article>
        get() = differ.currentList
        set(value) { differ.submitList(value) }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(ItemNewsBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = articles[position]

        val author = "Author : ${article.author ?: "Unknown"}"
        val urlImage = article.urlToImage ?: ""
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
        val publishedAt = "Published At : " + formatter.parse(article.publishedAt)


//      Set data in view
        holder.binding.apply {
            tvTitle.text = article.title
            tvPublishedAt.text = publishedAt
            tvAuthor.text = author

            if (urlImage.isNotEmpty()){
                Picasso.get().load(urlImage).into(ivImageArticle)
            }

        }

        holder.itemView.setOnClickListener {
            val bundle = bundleOf(
                "title" to article.title,
                "urlImage" to urlImage,
                "author" to article.author,
                "publishedAt" to article.publishedAt,
                "description" to article.description,
                "content" to article.content,
                "url" to article.url
            )

            navController.navigate(action, bundle)
        }
    }

    override fun getItemCount(): Int = articles.size
}