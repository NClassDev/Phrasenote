package com.phrasenote.data.model

import android.graphics.Bitmap

data class Resource(
    val id: Int = -1,
    val title: String = "",
    val author: String = "",
    val resource_image: Bitmap? = null
)

data class ResourceList(val results: List<Resource> = listOf())