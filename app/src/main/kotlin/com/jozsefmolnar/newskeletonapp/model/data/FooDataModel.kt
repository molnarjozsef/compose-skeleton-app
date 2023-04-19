package com.jozsefmolnar.newskeletonapp.model.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jozsefmolnar.newskeletonapp.model.domain.Foo

@Entity(tableName = "items")
data class FooDataModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val name: String,
) {
    fun toDomainModel() = Foo(
        id = id.toString(),
        name = name,
    )
}
