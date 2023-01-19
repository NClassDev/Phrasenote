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

    override suspend fun getAllResources(): ResourceList {
        return localDataSource.getAllResources()
    }

    override suspend fun getResourceByTitle(title: String): Resource? {
        val tempList = localDataSource.getAllResources()
        var resource = Resource()
        tempList.result.forEach { it ->
            if(it.title == title)
                resource = it
        }
        return resource
    }

    override suspend fun savePhrase(phrase: Phrase) {
        localDataSource.savePhrase(phrase.toPhraseEntity())
    }

    override suspend fun saveResource(resource: Resource) {
        localDataSource.saveResource(resource.toResourceEntity())
    }

    override suspend fun deletePhrase(phrase: Phrase) {
        localDataSource.deletePhrase(phrase.toPhraseEntity())
    }


}