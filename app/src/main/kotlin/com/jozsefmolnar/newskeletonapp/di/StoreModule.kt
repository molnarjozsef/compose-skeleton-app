package com.jozsefmolnar.newskeletonapp.di

import com.jozsefmolnar.newskeletonapp.store.DefaultFooStore
import com.jozsefmolnar.newskeletonapp.store.FooStore
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class StoreModule {

    @Binds
    abstract fun bindFooStore(fooStore: DefaultFooStore): FooStore

}
