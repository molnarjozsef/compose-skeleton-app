package com.jozsefmolnar.newskeletonapp.di

import com.jozsefmolnar.newskeletonapp.navigation.SimpleNavigator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NavigationModule {

    @Provides
    @Singleton
    fun provideNavigator() = SimpleNavigator()

}
