package com.phrasenote.ui.addphrase.validationphrase

import android.text.TextUtils

class PhraseDataValidation(
    override val resourceTitle: String,
    override val author: String,
    override val location: String,
    override val phrase: String,
    override val phraseExample: String,
    override val meaning: String,
    override val img: String

) : InterfacePhraseDataValidation {
    override fun isDataValid(): Int {
        if (TextUtils.isEmpty(resourceTitle))
            return 0
        else if (TextUtils.isEmpty(author))
            return 1
        else if (TextUtils.isEmpty(location))
            return 2
        else if (TextUtils.isEmpty(phrase))
            return 3
        else if (TextUtils.isEmpty(phraseExample))
            return 4
        else if (TextUtils.isEmpty(meaning))
            return 5
        else if (TextUtils.isEmpty(img) || img.length < 5)
            return 6
        else return -1
    }

}