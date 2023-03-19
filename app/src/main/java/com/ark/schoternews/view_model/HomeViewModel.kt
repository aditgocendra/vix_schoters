package com.ark.schoternews.view_model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ark.schoternews.data.models.Article
import com.ark.schoternews.data.repositories.NewsRepository
import kotlinx.coroutines.launch


class HomeViewModel(private val newsRepository : NewsRepository) : ViewModel() {

    var listArticle = MutableLiveData<List<Article>>()

    fun setArticles(query : String){
        viewModelScope.launch {
            val response = try {
                newsRepository.getNews(query = query)
            }catch (e : Exception){
                Log.e("Error", e.message.toString())
                return@launch
            }

            if (response.isSuccessful && response.body() != null) {
                Log.d("Test", response.code().toString())
                listArticle.value = response.body()!!.articles
            }
        }
    }

    fun setArticleLocal(){
        viewModelScope.launch {
            val result = newsRepository.getArticle()

            val articles : MutableList<Article> = mutableListOf()

            for (v in result){
               articles.add(Article(title = v.title, description = v.description!!, content = v.content, url = v.url, urlToImage = v.urlToImage!!, publishedAt = v.publishedAt, author = v.author!!, id = v.id))
            }

            listArticle.value = articles
        }
    }

}