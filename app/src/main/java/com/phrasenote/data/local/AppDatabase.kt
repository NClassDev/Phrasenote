package com.phrasenote.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.phrasenote.data.model.PhraseEntity
import com.phrasenote.data.model.ResourceEntity

@Database(entities = [PhraseEntity::class, ResourceEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun phraseDao(): PhraseDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            INSTANCE = INSTANCE ?: Room.databaseBuilder(
                context.applicationContext, AppDatabase::class.java, "phrase_table2"
            ).build()

            return INSTANCE!!
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}