package com.jozsefmolnar.newskeletonapp.mapper

import com.jozsefmolnar.newskeletonapp.model.data.FooDataModel
import com.jozsefmolnar.newskeletonapp.model.domain.Foo
import com.jozsefmolnar.newskeletonapp.util.ModelMapper
import javax.inject.Inject

class FooDataModelMapper @Inject constructor() : ModelMapper<FooDataModel, Foo> {
    override fun mapToDomainModel(model: FooDataModel) = Foo(
        name = model.name
    )

    override fun mapFromDomainModel(domainModel: Foo) = FooDataModel(
        name = domainModel.name
    )

    fun mapToDomainModelList(models: List<FooDataModel>) = models.map { mapToDomainModel(it) }

    fun mapFromDomainModelList(domainModels: List<Foo>) = domainModels.map { mapFromDomainModel(it) }
}
