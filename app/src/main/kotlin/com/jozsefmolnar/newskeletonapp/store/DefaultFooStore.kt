package com.jozsefmolnar.newskeletonapp.store

import com.jozsefmolnar.newskeletonapp.db.FooDao
import com.jozsefmolnar.newskeletonapp.mapper.FooDataModelMapper
import com.jozsefmolnar.newskeletonapp.model.domain.Foo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DefaultFooStore @Inject constructor(
    private val fooDao: FooDao,
    private val fooDataModelMapper: FooDataModelMapper,
) : FooStore {

    override fun getFooList(): Flow<List<Foo>> = fooDao.getAll()
        .map { fooDataModelMapper.mapToDomainModelList(it) }

    override fun getFoo(id: Int): Flow<Foo?> = fooDao.get(id)
        .map { fooDataModel -> fooDataModel?.let { fooDataModelMapper.mapToDomainModel(it) } }

}
