package com.jozsefmolnar.newskeletonapp.util

interface ModelMapper<Model, DomainModel> {

    fun mapToDomainModel(model: Model): DomainModel

    fun mapFromDomainModel(domainModel: DomainModel): Model
}
