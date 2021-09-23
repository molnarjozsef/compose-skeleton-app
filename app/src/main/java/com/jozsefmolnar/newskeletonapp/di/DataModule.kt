package com.jozsefmolnar.newskeletonapp.di

import android.content.Context
import androidx.room.Room
import com.jozsefmolnar.newskeletonapp.db.ArticleDatabase
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
    fun provideArticleDao(articleDatabase: ArticleDatabase) = articleDatabase.articleDao()

}
