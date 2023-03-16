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

}