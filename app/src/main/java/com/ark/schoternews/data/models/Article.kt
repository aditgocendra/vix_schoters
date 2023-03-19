package com.ark.schoternews.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
data class Article(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    @ColumnInfo(name = "author") val author: String?,
    @ColumnInfo(name = "content") val content: String,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "published_at") val publishedAt: String,
    @Ignore val source: Source?,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "url") val url: String,
    @ColumnInfo(name = "url_to_image") val urlToImage: String?
){
    // Secondary constructor
    // using this constructor for insert data to sqlite database
    constructor(
        id: Int,
        author : String,
        content : String,
        description : String,
        publishedAt : String,
        title : String,
        url : String,
        urlToImage : String
    ) : this(
            id,
            author,
            content,
            description,
            publishedAt,
            Source("0", ""),
            title,
            url,
            urlToImage
    )
}