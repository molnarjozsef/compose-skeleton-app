package com.jozsefmolnar.newskeletonapp.di

import com.jozsefmolnar.newskeletonapp.action.DefaultFooAction
import com.jozsefmolnar.newskeletonapp.action.FooAction
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ActionModule {

    @Binds
    abstract fun bindFooAction(fooAction: DefaultFooAction): FooAction

}
