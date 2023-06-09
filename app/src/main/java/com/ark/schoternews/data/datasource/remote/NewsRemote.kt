package com.ark.schoternews.data.datasource.remote

import com.ark.schoternews.BuildConfig
import com.ark.schoternews.data.models.ResponseNews
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsRemote {
    @GET("/v2/everything")
    suspend fun getNews(
        @Query("q") q : String,
        @Query("language") language : String,
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY
    ) : Response<ResponseNews>
}