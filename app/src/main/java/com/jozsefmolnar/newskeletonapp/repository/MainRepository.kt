package com.jozsefmolnar.newskeletonapp.repository

import com.jozsefmolnar.newskeletonapp.mapper.NetworkMapper
import com.jozsefmolnar.newskeletonapp.model.domain.Foo
import com.jozsefmolnar.newskeletonapp.service.FooService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val fooService: FooService,
    private val networkMapper: NetworkMapper,
) {

    suspend fun getLatestFoo(): Flow<List<Foo>> = flow {
        val latestFoo = fooService.getLatestFoo()
        val articles = networkMapper.mapFromEntityList(latestFoo.items)
        emit(articles)
    }
}