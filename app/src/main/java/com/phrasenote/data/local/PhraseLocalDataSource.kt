package com.phrasenote.data.local

import com.phrasenote.data.model.*

class PhraseLocalDataSource(private val phraseDao: PhraseDao) {

    suspend fun getPhrases(): PhraseList {
        return phraseDao.getAllPhrases().toPhraseList()
    }

   suspend fun savePhrase(phraseEntity: PhraseEntity) {
        phraseDao.savePhrase(phraseEntity)
    }

    suspend fun saveResource(resourceEntity: ResourceEntity) {
        phraseDao.saveResource(resourceEntity)
    }

    suspend fun getAllResources(): ResourceList{
        return phraseDao.getAllResources().toResourceList()
    }
}
