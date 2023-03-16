package com.ark.schoternews.core.utility

import com.ark.schoternews.data.datasource.remote.NewsRemote
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api: NewsRemote by lazy {
        Retrofit.Builder()
            .baseUrl("https://newsapi.org")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsRemote::class.java)
    }
}