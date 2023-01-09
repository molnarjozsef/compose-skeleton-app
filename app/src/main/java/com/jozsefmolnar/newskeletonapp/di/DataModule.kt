package com.jozsefmolnar.newskeletonapp.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.room.Room
import com.jozsefmolnar.newskeletonapp.db.ArticleDatabase
import com.jozsefmolnar.newskeletonapp.db.WeatherDatabase
import com.jozsefmolnar.newskeletonapp.db.dataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataModule {

    @Singleton
    @Provides
    fun provideArticleDatabase(@ApplicationContext context: Context): ArticleDatabase =
        Room.databaseBuilder(
            context,
            ArticleDatabase::class.java,
            ArticleDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun provideWeatherDatabase(@ApplicationContext context: Context): WeatherDatabase =
        Room.databaseBuilder(
            context,
            WeatherDatabase::class.java,
            WeatherDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun provideArticleDao(articleDatabase: ArticleDatabase) = articleDatabase.articleDao()

    @Singleton
    @Provides
    fun provideWeatherDao(weatherDatabase: WeatherDatabase) = weatherDatabase.weatherDao()

    @Singleton
    @Provides
    fun providePreferencesDataStore(
        @ApplicationContext applicationContext: Context,
    ): DataStore<Preferences> =
        applicationContext.dataStore
}
