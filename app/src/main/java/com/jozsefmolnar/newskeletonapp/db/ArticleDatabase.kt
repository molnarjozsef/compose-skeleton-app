package com.jozsefmolnar.newskeletonapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jozsefmolnar.newskeletonapp.model.data.ArticleDataModel

@Database(entities = [ArticleDataModel::class], version = 1)
abstract class ArticleDatabase : RoomDatabase() {

    abstract fun articleDao(): ArticleDao

    companion object {

        const val DATABASE_NAME: String = "article_db"
    }
}
