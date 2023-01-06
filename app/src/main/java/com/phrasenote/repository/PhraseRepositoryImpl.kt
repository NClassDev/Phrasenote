package com.phrasenote.repository

import com.phrasenote.data.local.PhraseLocalDataSource
import com.phrasenote.data.model.*

class PhraseRepositoryImpl(private val localDataSource: PhraseLocalDataSource): PhraseRepository {

    override suspend fun getAllPhrases(): PhraseList {
        if (localDataSource.getPhrases().results.isEmpty()) {
            val phraseTemp = Phrase()
            localDataSource.savePhrase(phraseTemp.PhraseDummie().toPhraseEntity())
        }
        return localDataSource.getPhrases()
    }

    override suspend fun savePhrase(phrase: Phrase) {
        localDataSource.savePhrase(phrase.toPhraseEntity())
    }

}