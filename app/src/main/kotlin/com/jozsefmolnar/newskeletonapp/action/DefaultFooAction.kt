package com.jozsefmolnar.newskeletonapp.action

import com.jozsefmolnar.newskeletonapp.db.FooDao
import com.jozsefmolnar.newskeletonapp.service.FooService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class DefaultFooAction @Inject constructor(
    private val fooService: FooService,
    private val fooDao: FooDao,
) : FooAction {

    override suspend fun refreshFooList() {
        withContext(Dispatchers.IO) {
            try {
                val latestFoo = fooService.getLatestFoo()
                val fooDataModels = latestFoo.items.map { it.toDataModel() }
                fooDao.clearAll()
                fooDao.insertAll(fooDataModels)
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }

}
