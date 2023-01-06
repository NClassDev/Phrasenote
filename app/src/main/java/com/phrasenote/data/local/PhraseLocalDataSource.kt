package com.phrasenote.data.local

import com.phrasenote.data.model.PhraseEntity
import com.phrasenote.data.model.PhraseList
import com.phrasenote.data.model.toPhraseList

class PhraseLocalDataSource(private val phraseDao: PhraseDao) {

    suspend fun getPhrases(): PhraseList {
        return phraseDao.getAllPhrases().toPhraseList()
    }

   suspend fun savePhrase(phraseEntity: PhraseEntity) {
        phraseDao.savePhrase(phraseEntity)
    }

}
