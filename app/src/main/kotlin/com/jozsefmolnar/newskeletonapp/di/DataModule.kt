package com.jozsefmolnar.newskeletonapp.di

import android.content.Context
import androidx.room.Room
import com.jozsefmolnar.newskeletonapp.db.FooDatabase
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
    fun provideFooDatabase(@ApplicationContext context: Context): FooDatabase =
        Room.databaseBuilder(
            context,
            FooDatabase::class.java,
            FooDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun provideFooDao(fooDatabase: FooDatabase) = fooDatabase.fooDao()
}
