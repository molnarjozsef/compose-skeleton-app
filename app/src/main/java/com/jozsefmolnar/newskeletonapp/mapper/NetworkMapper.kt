package com.jozsefmolnar.newskeletonapp.mapper

import com.jozsefmolnar.newskeletonapp.model.api.FooApiModel
import com.jozsefmolnar.newskeletonapp.model.domain.Foo
import com.jozsefmolnar.newskeletonapp.util.EntityMapper
import javax.inject.Inject

class NetworkMapper @Inject constructor() : EntityMapper<FooApiModel, Foo> {
    override fun mapFromEntity(entity: FooApiModel) = Foo(
        name = entity.name
    )

    override fun mapToEntity(domainModel: Foo) = FooApiModel(
        name = domainModel.name
    )

    fun mapFromEntityList(entities: List<FooApiModel>) = entities.map { mapFromEntity(it) }
}