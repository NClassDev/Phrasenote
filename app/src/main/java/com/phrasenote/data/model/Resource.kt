package com.phrasenote.data.model

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
data class Resource(
    var id: Int = 0,
    val title: String = "",
    val author: String = "",
    val resource_image: String = ""
) : Serializable, Parcelable

//Room
@Entity
data class ResourceEntity(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    val id: Int = 0,
    @ColumnInfo(name = "title")
    val title: String = "",
    @ColumnInfo(name = "author")
    val author: String = "",
    @ColumnInfo(name = "resource_image")
    val resource_image: String = ""
)

fun ResourceEntity.toResource(): Resource = Resource(
    this.id,
    this.title,
    this.author,
    this.resource_image
)

fun Resource.toResourceEntity(): ResourceEntity = ResourceEntity(
    this.id,
    this.title,
    this.author,
    this.resource_image
)

fun List<ResourceEntity>.toResourceList(): ResourceList {
    val resultList = mutableListOf<Resource>()
    this.forEach { resourceEntity ->
        resultList.add(resourceEntity.toResource())
    }
    return ResourceList(resultList)
}

data class ResourceList(val results: List<Resource> = listOf())