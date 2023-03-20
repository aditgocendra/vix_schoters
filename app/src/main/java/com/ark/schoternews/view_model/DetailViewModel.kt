package com.ark.schoternews.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ark.schoternews.data.models.Article
import com.ark.schoternews.data.repositories.NewsRepository
import kotlinx.coroutines.launch

class DetailViewModel(private val newsRepository: NewsRepository) : ViewModel() {
    val selectArticleData = MutableLiveData<Article>()

    fun selectArticle(title : String) {
        viewModelScope.launch {
            val result = try {
                newsRepository.selectArticle(title)
            }catch (e : Exception){
                return@launch
            }

            selectArticleData.value = result
        }
    }

    fun bookmarkAdd(article: Article){
        viewModelScope.launch {
            newsRepository.addArticle(article = article)
        }
    }
}