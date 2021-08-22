package com.jozsefmolnar.newskeletonapp.mapper

import com.jozsefmolnar.newskeletonapp.model.api.FooApiModel
import com.jozsefmolnar.newskeletonapp.model.domain.Foo
import com.jozsefmolnar.newskeletonapp.util.ModelMapper
import javax.inject.Inject

class FooApiModelMapper @Inject constructor() : ModelMapper<FooApiModel, Foo> {

    override fun mapToDomainModel(model: FooApiModel) = Foo(
        name = model.name
    )

    override fun mapFromDomainModel(domainModel: Foo) = FooApiModel(
        name = domainModel.name
    )

    fun mapToDomainModelList(models: List<FooApiModel>) = models.map { mapToDomainModel(it) }
}
