package com.phrasenote.repository

import android.provider.ContactsContract.CommonDataKinds.StructuredName
import com.phrasenote.data.model.*

interface PhraseRepository {
    suspend fun getAllPhrases(): PhraseList
    suspend fun getAllResources(): ResourceList
    suspend fun savePhrase(phrase: Phrase)
    suspend fun saveResource(resource: Resource)
    suspend fun deletePhrase(phrase:Phrase)
    suspend fun getResourceByTitle(title: String): Resource?

}