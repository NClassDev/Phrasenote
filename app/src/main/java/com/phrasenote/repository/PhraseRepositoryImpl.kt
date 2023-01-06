package com.phrasenote.repository

import com.phrasenote.data.local.PhraseLocalDataSource
import com.phrasenote.data.model.Phrase
import com.phrasenote.data.model.PhraseEntity
import com.phrasenote.data.model.PhraseList
import com.phrasenote.data.model.toPhraseEntity

class PhraseRepositoryImpl(private val localDataSource: PhraseLocalDataSource): PhraseRepository {
    override suspend fun getAllPhrases(): PhraseList {
        return localDataSource.getPhrases()
    }

    override suspend fun savePhrase(phrase: Phrase) {
        localDataSource.savePhrase(phrase.toPhraseEntity())
    }

}