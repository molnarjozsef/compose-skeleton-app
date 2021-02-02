package com.jozsefmolnar.newskeletonapp.repository

import com.jozsefmolnar.newskeletonapp.mapper.NetworkMapper
import com.jozsefmolnar.newskeletonapp.model.domain.Article
import com.jozsefmolnar.newskeletonapp.service.NewsService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val newsService: NewsService,
    private val networkMapper: NetworkMapper,
) {

    suspend fun getLatestNews(): Flow<List<Article>> = flow {
        val latestNews = newsService.getLatestNews()
        val articles = networkMapper.mapFromEntityList(latestNews.articles)
        emit(articles)
    }
}