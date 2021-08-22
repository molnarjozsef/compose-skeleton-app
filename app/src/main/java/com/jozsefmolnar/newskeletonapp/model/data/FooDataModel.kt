package com.jozsefmolnar.newskeletonapp.model.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")
data class FooDataModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val name: String
)
