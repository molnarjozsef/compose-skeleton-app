package com.jozsefmolnar.newskeletonapp.mapper

import com.jozsefmolnar.newskeletonapp.model.api.ArticleApiModel
import com.jozsefmolnar.newskeletonapp.model.domain.Article
import com.jozsefmolnar.newskeletonapp.util.ModelMapper
import javax.inject.Inject

class ApiModelMapper @Inject constructor() : ModelMapper<ArticleApiModel, Article> {
    override fun mapToDomainModel(model: ArticleApiModel) = Article(
        title = model.title,
        description = model.description,
        url = model.url,
        urlToImage = model.urlToImage,
        publishedAt = model.publishedAt,
        content = model.content,
        author = model.author?.takeIf { it.isNotBlank() },
        source = model.source?.name?.takeIf { it.isNotBlank() }
    )

    override fun mapFromDomainModel(domainModel: Article) = ArticleApiModel(
        title = domainModel.title,
        description = domainModel.description,
        url = domainModel.url,
        urlToImage = domainModel.urlToImage,
        publishedAt = domainModel.publishedAt,
        content = domainModel.content,
        source = null,
        author = domainModel.author
    )

    fun mapToDomainModelList(models: List<ArticleApiModel>) = models.map { mapToDomainModel(it) }
}
