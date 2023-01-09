package com.jozsefmolnar.newskeletonapp.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.jozsefmolnar.newskeletonapp.db.ArticleDao
import com.jozsefmolnar.newskeletonapp.db.PreferencesKeys
import com.jozsefmolnar.newskeletonapp.model.domain.Article
import com.jozsefmolnar.newskeletonapp.service.NewsService
import com.jozsefmolnar.newskeletonapp.util.Constants
import com.jozsefmolnar.newskeletonapp.util.DateTimeUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val newsService: NewsService,
    private val articleDao: ArticleDao,
    private val settingsRepository: SettingsRepository,
    private val dataStore: DataStore<Preferences>,
) {

    fun getCachedNews(): Flow<List<Article>> = articleDao.getAll()
        .map { dataModels -> dataModels.map { it.mapToDomainModel() } }

    fun getCachedArticle(id: Int): Flow<Article?> = articleDao.get(id)
        .map { it?.mapToDomainModel() }

    suspend fun fetchLatestNews(forced: Boolean = false) {
        val lastRefreshedTime = dataStore.data.map { it[PreferencesKeys.NewsLastRefreshTime] }.first() ?: 0
        if (!forced && System.currentTimeMillis() - lastRefreshedTime < Constants.OneHourInMillis) {
            return
        }

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

            val articles = latestArticles.map { it.mapToDomainModel() }
            val articleDataModels = articles.map { it.mapToDataModel() }
            articleDao.clearAll()
            articleDao.insertAll(articleDataModels)

            dataStore.edit { preferences ->
                preferences[PreferencesKeys.NewsLastRefreshTime] = System.currentTimeMillis()
            }
        } catch (e: Exception) {
            Timber.e(e)
        }
    }
}
