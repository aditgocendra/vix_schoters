package com.ark.schoternews.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ark.schoternews.data.models.Article
import com.ark.schoternews.data.repositories.NewsRepository
import kotlinx.coroutines.launch

class DetailViewModel(private val newsRepository: NewsRepository) : ViewModel() {

    fun bookmarkArticle(article : Article){
        viewModelScope.launch {
            newsRepository.addArticle(article)
        }
    }
}