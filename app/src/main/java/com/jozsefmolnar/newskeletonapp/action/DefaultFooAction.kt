package com.jozsefmolnar.newskeletonapp.action

import com.jozsefmolnar.newskeletonapp.db.FooDao
import com.jozsefmolnar.newskeletonapp.mapper.FooApiModelMapper
import com.jozsefmolnar.newskeletonapp.mapper.FooDataModelMapper
import com.jozsefmolnar.newskeletonapp.service.FooService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class DefaultFooAction @Inject constructor(
    private val fooService: FooService,
    private val fooDao: FooDao,
    private val fooApiModelMapper: FooApiModelMapper,
    private val fooDataModelMapper: FooDataModelMapper,
) : FooAction {

    override suspend fun fetchLatestFoo() {
        withContext(Dispatchers.IO) {
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

}
