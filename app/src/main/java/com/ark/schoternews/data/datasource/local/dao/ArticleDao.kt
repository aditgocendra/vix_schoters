package com.ark.schoternews.data.datasource.local.dao

import androidx.room.*
import com.ark.schoternews.data.models.Article

@Dao
interface ArticleDao {
    @Insert
    suspend fun add(article : Article)

    @Update
    suspend fun update(article : Article)

    @Delete
    suspend fun delete(article : Article)

    @Query("SELECT * FROM article")
    fun get() : List<Article>

    @Query("DELETE FROM article")
    fun deleteAllRecord()


}