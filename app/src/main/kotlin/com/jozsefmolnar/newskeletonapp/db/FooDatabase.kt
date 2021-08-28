package com.jozsefmolnar.newskeletonapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jozsefmolnar.newskeletonapp.model.data.FooDataModel

@Database(entities = [FooDataModel::class], version = 1)
abstract class FooDatabase : RoomDatabase() {

    abstract fun fooDao(): FooDao

    companion object {

        const val DATABASE_NAME: String = "foo_db"
    }
}
