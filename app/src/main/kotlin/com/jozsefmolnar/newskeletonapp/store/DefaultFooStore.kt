package com.jozsefmolnar.newskeletonapp.store

import com.jozsefmolnar.newskeletonapp.db.FooDao
import com.jozsefmolnar.newskeletonapp.model.domain.Foo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DefaultFooStore @Inject constructor(
    private val fooDao: FooDao,
) : FooStore {

    override fun getFooList(): Flow<List<Foo>> = fooDao.getAll()
        .map { dataModels ->
            dataModels.map { it.toDomainModel() }
        }

    override fun getFoo(id: Int): Flow<Foo?> = fooDao.get(id)
        .map { fooDataModel -> fooDataModel?.toDomainModel() }
}
