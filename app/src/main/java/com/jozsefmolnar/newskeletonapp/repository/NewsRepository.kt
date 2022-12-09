package com.jozsefmolnar.newskeletonapp.repository

import com.jozsefmolnar.newskeletonapp.db.ArticleDao
import com.jozsefmolnar.newskeletonapp.mapper.ApiModelMapper
import com.jozsefmolnar.newskeletonapp.mapper.DataModelMapper
import com.jozsefmolnar.newskeletonapp.model.domain.Article
import com.jozsefmolnar.newskeletonapp.service.NewsService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val newsService: NewsService,
    private val articleDao: ArticleDao,
    private val apiModelMapper: ApiModelMapper,
    private val dataModelMapper: DataModelMapper,
) {

    fun getCachedNews(): Flow<List<Article>> = articleDao.getAll()
        .map { dataModelMapper.mapToDomainModelList(it) }

    fun getCachedArticle(id: Int): Flow<Article?> = articleDao.get(id)
        .map { articleDataModel -> articleDataModel?.let { dataModelMapper.mapToDomainModel(it) } }

    suspend fun fetchLatestNews() {
        try {
            val latestNews = newsService.getLatestNews()
            val articles = apiModelMapper.mapToDomainModelList(latestNews.articles)
            val articleDataModels = dataModelMapper.mapFromDomainModelList(articles)
            articleDao.clearAll()
            articleDao.insertAll(articleDataModels)
        } catch (e: Exception) {
            Timber.e(e)
        }
    }
}
