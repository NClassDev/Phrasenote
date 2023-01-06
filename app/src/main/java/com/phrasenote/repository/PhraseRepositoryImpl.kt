package com.phrasenote.repository

import com.phrasenote.data.local.PhraseLocalDataSource
import com.phrasenote.data.model.*

class PhraseRepositoryImpl(private val localDataSource: PhraseLocalDataSource): PhraseRepository {

    override suspend fun getAllPhrases(): PhraseList {
        if (localDataSource.getPhrases().results.size < 1) {
            val phraseTemp = Phrase()
            //TODO Remove the lines above before the release version
            localDataSource.savePhrase(phraseTemp.PhraseDummie1().toPhraseEntity())
            localDataSource.savePhrase(phraseTemp.PhraseDummie2().toPhraseEntity())
        }
        return localDataSource.getPhrases()
    }

    override suspend fun savePhrase(phrase: Phrase) {
        localDataSource.savePhrase(phrase.toPhraseEntity())
    }

}