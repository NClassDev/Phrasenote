package com.phrasenote.ui.addphrase

interface InterfacePhraseDataValidation {
    val resourceTitle: String
    val author: String
    val location: String
    val phrase: String
    val phraseExample: String
    val meaning: String
    val img: String
    fun isDataValid(): Int
}