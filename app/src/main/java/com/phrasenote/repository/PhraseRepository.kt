package com.phrasenote.repository

import com.phrasenote.data.model.Phrase
import com.phrasenote.data.model.PhraseList

interface PhraseRepository {
    suspend fun getAllPhrases(): PhraseList
    suspend fun savePhrase(phrase: Phrase)
}