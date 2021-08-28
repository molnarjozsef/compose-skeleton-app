package com.jozsefmolnar.newskeletonapp.store

import com.jozsefmolnar.newskeletonapp.model.domain.Foo
import kotlinx.coroutines.flow.Flow

interface FooStore {

    fun getFooList(): Flow<List<Foo>>

    fun getFoo(id: Int): Flow<Foo?>

}
