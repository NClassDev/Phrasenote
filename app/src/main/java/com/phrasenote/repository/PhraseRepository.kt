package com.phrasenote.repository

import android.provider.ContactsContract.CommonDataKinds.StructuredName
import com.phrasenote.data.model.Phrase
import com.phrasenote.data.model.PhraseList
import com.phrasenote.data.model.Resource
import com.phrasenote.data.model.ResourceList

interface PhraseRepository {
    suspend fun getAllPhrases(): PhraseList
    suspend fun getAllResources(): ResourceList
    suspend fun savePhrase(phrase: Phrase)
    suspend fun saveResource(resource: Resource)
    suspend fun getResourceByTitle(title: String): Resource?
}