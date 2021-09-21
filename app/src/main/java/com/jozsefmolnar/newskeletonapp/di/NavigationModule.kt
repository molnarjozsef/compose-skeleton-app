package com.jozsefmolnar.newskeletonapp.di

import com.jozsefmolnar.newskeletonapp.navigation.Navigator
import com.jozsefmolnar.newskeletonapp.navigation.RouteManager
import com.jozsefmolnar.newskeletonapp.navigation.impl.NavigatorImpl
import com.jozsefmolnar.newskeletonapp.navigation.impl.RouteManagerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NavigationModule {

    @Binds
    @Singleton
    abstract fun NavigatorImpl.bindNavigator(): Navigator

    @Binds
    @Singleton
    abstract fun RouteManagerImpl.bindRouteManager(): RouteManager
}
