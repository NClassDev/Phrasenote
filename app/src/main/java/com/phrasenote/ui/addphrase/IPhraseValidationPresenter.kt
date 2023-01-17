package com.phrasenote.ui.addphrase

interface IPhraseValidationPresenter {
    fun onValidation(
        resourceTitle: String,
        author: String,
        location: String,
        phrase: String,
        phraseExample: String,
        meaning: String,
        img: String
    )
}