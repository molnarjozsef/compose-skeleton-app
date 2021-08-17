package com.jozsefmolnar.newskeletonapp.repository

import com.jozsefmolnar.newskeletonapp.db.FooDao
import com.jozsefmolnar.newskeletonapp.mapper.FooApiModelMapper
import com.jozsefmolnar.newskeletonapp.mapper.FooDataModelMapper
import com.jozsefmolnar.newskeletonapp.model.domain.Foo
import com.jozsefmolnar.newskeletonapp.service.FooService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val fooService: FooService,
    private val fooDao: FooDao,
    private val fooApiModelMapper: FooApiModelMapper,
    private val fooDataModelMapper: FooDataModelMapper,
) {

    fun getCachedFoo(): Flow<List<Foo>> = fooDao.getAll()
        .map { fooDataModelMapper.mapToDomainModelList(it) }

    suspend fun fetchLatestFoo() {
        try {
            val latestFoo = fooService.getLatestFoo()
            val items = fooApiModelMapper.mapToDomainModelList(latestFoo.items)
            val fooDataModels = fooDataModelMapper.mapFromDomainModelList(items)
            fooDao.clearAll()
            fooDao.insertAll(fooDataModels)
        } catch (e: Exception) {
            Timber.e(e)
        }
    }
}
