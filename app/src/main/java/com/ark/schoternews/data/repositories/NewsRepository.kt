package com.ark.schoternews.data.repositories

import com.ark.schoternews.core.utility.RetrofitInstance
import com.ark.schoternews.data.models.ResponseNews
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class NewsRepository {

    suspend fun getNews() : Response<ResponseNews> {
        return withContext(Dispatchers.IO){
            RetrofitInstance.api.getNews("indonesia", "id")
        }
    }
}