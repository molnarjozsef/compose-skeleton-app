package com.jozsefmolnar.newskeletonapp.mapper

import com.jozsefmolnar.newskeletonapp.model.api.ArticleApiModel
import com.jozsefmolnar.newskeletonapp.model.domain.Article
import com.jozsefmolnar.newskeletonapp.util.EntityMapper
import javax.inject.Inject

class NetworkMapper @Inject constructor() : EntityMapper<ArticleApiModel, Article> {
    override fun mapFromEntity(entity: ArticleApiModel) = Article(
        title = entity.title,
        description = entity.description,
        url = entity.url,
        urlToImage = entity.urlToImage,
        publishedAt = entity.publishedAt,
        content = entity.content,
    )

    override fun mapToEntity(domainModel: Article) = ArticleApiModel(
        title = domainModel.title,
        description = domainModel.description,
        url = domainModel.url,
        urlToImage = domainModel.urlToImage,
        publishedAt = domainModel.publishedAt,
        content = domainModel.content,
        source = null,
        author = null
    )

    fun mapFromEntityList(entities: List<ArticleApiModel>) = entities.map { mapFromEntity(it) }
}