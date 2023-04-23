package com.phrasenote.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.phrasenote.data.model.PhraseEntity
import com.phrasenote.data.model.Resource
import com.phrasenote.data.model.ResourceEntity
import com.phrasenote.data.model.ResourceList

@Dao
interface PhraseDao {

    @Query("SELECT * FROM phraseentity")
    suspend fun getAllPhrases(): List<PhraseEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePhrase(phraseEntity: PhraseEntity)

    @Query("SELECT * FROM resourceentity")
    suspend fun getAllResources(): List<ResourceEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveResource(resourceEntity: ResourceEntity)

    @Delete
    suspend fun deletePhrase(phraseEntity: PhraseEntity)

}