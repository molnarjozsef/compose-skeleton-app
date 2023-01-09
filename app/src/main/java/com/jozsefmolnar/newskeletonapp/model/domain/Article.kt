package com.jozsefmolnar.newskeletonapp.model.domain

import com.jozsefmolnar.newskeletonapp.model.data.ArticleDataModel

data class Article(
    val id: Int? = null,
    val title: String,
    val description: String?,
    val url: String,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String?,
    val author: String?,
    val source: String?,
) {
    fun mapToDataModel() =
        ArticleDataModel(
            title = title,
            description = description,
            url = url,
            urlToImage = urlToImage,
            publishedAt = publishedAt,
            content = content,
            author = author,
            source = source,
        )
}
