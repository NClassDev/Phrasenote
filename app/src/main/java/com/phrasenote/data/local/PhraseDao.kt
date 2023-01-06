package com.phrasenote.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.phrasenote.data.model.PhraseEntity

@Dao
interface PhraseDao {
    @Query("SELECT * FROM phraseentity")
    suspend fun getAllPhrases(): List<PhraseEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePhrase(phraseEntity: PhraseEntity)
    
}