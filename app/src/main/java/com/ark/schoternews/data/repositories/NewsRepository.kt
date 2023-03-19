package com.ark.schoternews.data.repositories

import com.ark.schoternews.data.datasource.local.AppDatabase
import com.ark.schoternews.data.datasource.remote.NewsRemote
import com.ark.schoternews.data.models.Article
import com.ark.schoternews.data.models.ResponseNews
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class NewsRepository(private val dbLocal : AppDatabase, private val newsRemote : NewsRemote) {

    suspend fun getNews(query : String) : Response<ResponseNews> {
        return withContext(Dispatchers.IO){
            newsRemote.getNews(query, "id")
        }
    }

    suspend fun getArticle() : List<Article> {
        return withContext(Dispatchers.IO){
            dbLocal.articleDao().get()
        }
    }

    suspend fun addArticle(article : Article) {
        withContext(Dispatchers.IO){
            dbLocal.articleDao().add(article)
        }
    }
}