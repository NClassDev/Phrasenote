package com.phrasenote.ui.addphrase.validationphrase

interface IPhraseDataValidationMessage {
    fun onPhraseValidationSuccess(message: String)
    fun onPhraseValidationError(message: String)
}