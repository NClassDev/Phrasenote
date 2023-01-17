package com.phrasenote.ui.addphrase

class PhraseValidationPresenter(private var iPhraseDataValidationMessage: IPhraseDataValidationMessage ) : IPhraseValidationPresenter{
    override fun onValidation(
        resourceTitle: String,
        author: String,
        location: String,
        phrase: String,
        phraseExample: String,
        meaning: String,
        img: String
    ) {
        var phrase = PhraseDataValidation(resourceTitle, author, location, phrase, phraseExample, meaning, img)
        val phraseCode = phrase.isDataValid()

        when (phraseCode) {
            0 -> iPhraseDataValidationMessage.onPhraseValidationError("Ingresa un titulo a tu frase")
            1 -> iPhraseDataValidationMessage.onPhraseValidationError("Ingresa un author a tu frase")
            2 -> iPhraseDataValidationMessage.onPhraseValidationError("Ingresa una ubicacion a tu frase")
            3 -> iPhraseDataValidationMessage.onPhraseValidationError("Ingresa tu frase")
            4 -> iPhraseDataValidationMessage.onPhraseValidationError("Ingresa un ejemplo de tu frase")
            5 -> iPhraseDataValidationMessage.onPhraseValidationError("Ingresa un significado")
            6 -> iPhraseDataValidationMessage.onPhraseValidationError("Ingresa una imagen a tu frase")
            else -> iPhraseDataValidationMessage.onPhraseValidationSuccess("Guardando phrase...")
        }

    }
}