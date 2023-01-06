package com.phrasenote.data.model

import android.graphics.Bitmap

data class PhraseResource(
    val id: Int = -1,
    val title: String = "",
    val author: String = "",
    val resource_image: Bitmap? = null
)

data class PhraseResourceList(val results: List<PhraseResource> = listOf())