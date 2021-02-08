package com.jozsefmolnar.newskeletonapp.mapper

import com.jozsefmolnar.newskeletonapp.model.data.ArticleDataModel
import com.jozsefmolnar.newskeletonapp.model.domain.Article
import com.jozsefmolnar.newskeletonapp.util.ModelMapper
import javax.inject.Inject

class DataModelMapper @Inject constructor() : ModelMapper<ArticleDataModel, Article> {
    override fun mapToDomainModel(model: ArticleDataModel) = Article(
        id = model.id!!,
        title = model.title,
        description = model.description,
        url = model.url,
        urlToImage = model.urlToImage,
        publishedAt = model.publishedAt,
        content = model.content
    )

    override fun mapFromDomainModel(domainModel: Article) = ArticleDataModel(
        title = domainModel.title,
        description = domainModel.description,
        url = domainModel.url,
        urlToImage = domainModel.urlToImage,
        publishedAt = domainModel.publishedAt,
        content = domainModel.content
    )

    fun mapToDomainModelList(models: List<ArticleDataModel>) = models.map { mapToDomainModel(it) }

    fun mapFromDomainModelList(domainModels: List<Article>) = domainModels.map { mapFromDomainModel(it) }
}
