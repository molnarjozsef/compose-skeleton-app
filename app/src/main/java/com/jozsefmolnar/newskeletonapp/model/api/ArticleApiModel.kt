package com.jozsefmolnar.newskeletonapp.model.api

import com.squareup.moshi.Json

@Json(name = "Article")
data class ArticleApiModel(
	val title: String,
	val description: String?,
	val url: String,
	val urlToImage: String?,
	val publishedAt: String,
	val content: String?,
	val source: SourceApiModel?,
	val author: String?,
)