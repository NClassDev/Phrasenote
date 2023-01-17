package com.phrasenote.ui.addphrase

interface IPhraseDataValidationMessage {
    fun onPhraseValidationSuccess(message: String)
    fun onPhraseValidationError(message: String)
}