package com.phrasenote.data.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class Phrase(
    val id: Int = -1,
    val title: String = "",
    val author: String = "",
    val location: String = "",
    val phrase: String = "",
    val phrase_example: String = "",
    val meaning: String = "",
    val create_at: String = "",
    val likes: Long = -1,
    val resource: String = ""
)

//Room
@Entity
data class PhraseEntity(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    val id: Int = -1,
    @ColumnInfo(name = "title")
    val title: String = "",
    @ColumnInfo(name = "author")
    val author: String = "",
    @ColumnInfo(name = "location")
    val location: String = "",
    @ColumnInfo(name = "phrase")
    val phrase: String = "",
    @ColumnInfo(name = "phrase_example")
    val phrase_example: String = "",
    @ColumnInfo(name = "meaning")
    val meaning: String = "",
    @ColumnInfo(name = "create_at")
    val create_at: String = "",
    @ColumnInfo(name = "likes")
    val likes: Long = -1,
    @ColumnInfo(name = "resource")
    val resource: String = ""
)

fun PhraseEntity.toPhrase(): Phrase = Phrase(
    this.id,
    this.title,
    this.author,
    this.location,
    this.phrase,
    this.phrase_example,
    this.meaning,
    this.create_at,
    this.likes,
    this.resource
)

fun Phrase.toPhraseEntity(): PhraseEntity = PhraseEntity(
    this.id,
    this.title,
    this.author,
    this.location,
    this.phrase,
    this.phrase_example,
    this.meaning,
    this.create_at,
    this.likes,
    this.resource
)

fun Phrase.PhraseDummie(): Phrase = Phrase(
    title = "The Perks of Being a Wallflower",
    author = "Stephen Chbosky",
    location= "Page 12",
    phrase= "get along with",
    phrase_example= "How are you getting along \n" +
            "with the training course”",
    meaning= "Have a good relationship with some one",
    create_at= "Created at May12, 2022 ",
    likes= 12,
    resource = "The Perks of Being a Wallflower"
)

fun List<PhraseEntity>.toPhraseList(): PhraseList {
    val resultList = mutableListOf<Phrase>()
    this.forEach { phraseEntity ->
        resultList.add(phraseEntity.toPhrase())
    }
    return PhraseList(resultList)
}

data class PhraseList(val results: List<Phrase> = listOf())