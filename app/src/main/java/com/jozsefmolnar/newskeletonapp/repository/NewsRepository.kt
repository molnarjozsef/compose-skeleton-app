package com.jozsefmolnar.newskeletonapp.repository

import com.jozsefmolnar.newskeletonapp.db.ArticleDao
import com.jozsefmolnar.newskeletonapp.mapper.ApiModelMapper
import com.jozsefmolnar.newskeletonapp.mapper.DataModelMapper
import com.jozsefmolnar.newskeletonapp.model.domain.Article
import com.jozsefmolnar.newskeletonapp.service.NewsService
import com.jozsefmolnar.newskeletonapp.util.DateTimeUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val newsService: NewsService,
    private val articleDao: ArticleDao,
    private val apiModelMapper: ApiModelMapper,
    private val dataModelMapper: DataModelMapper,
    private val settingsRepository: SettingsRepository,
) {

    fun getCachedNews(): Flow<List<Article>> = articleDao.getAll()
        .map { dataModelMapper.mapToDomainModelList(it) }

    fun getCachedArticle(id: Int): Flow<Article?> = articleDao.get(id)
        .map { articleDataModel -> articleDataModel?.let { dataModelMapper.mapToDomainModel(it) } }

    suspend fun fetchLatestNews() {
        try {
            val selectedCountries = settingsRepository.getSelectedCountries().first()

            val latestArticles = selectedCountries
                .map { country ->
                    newsService.getLatestNews(countryCode = country.countryCode).articles
                }
                .flatten()
                .sortedByDescending { article ->
                    DateTimeUtils.parseArticleDateTime(article.publishedAt)?.time
                }

            val articles = apiModelMapper.mapToDomainModelList(latestArticles)
            val articleDataModels = dataModelMapper.mapFromDomainModelList(articles)
            articleDao.clearAll()
            articleDao.insertAll(articleDataModels)
        } catch (e: Exception) {
            Timber.e(e)
        }
    }
}
