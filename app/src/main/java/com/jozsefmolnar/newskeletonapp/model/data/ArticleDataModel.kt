package com.jozsefmolnar.newskeletonapp.model.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jozsefmolnar.newskeletonapp.model.domain.Article

@Entity(tableName = "articles")
data class ArticleDataModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val title: String,
    val description: String?,
    val url: String,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String?,
    val author: String?,
    val source: String?,
) {
    fun mapToDomainModel() =
        Article(
            id = id!!,
            title = title,
            description = description,
            url = url,
            urlToImage = urlToImage,
            publishedAt = publishedAt,
            content = content,
            author = author,
            source = source
        )
}
