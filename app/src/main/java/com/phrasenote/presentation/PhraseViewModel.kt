package com.phrasenote.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.phrasenote.core.Resource
import com.phrasenote.data.model.Phrase
import com.phrasenote.repository.PhraseRepository
import kotlinx.coroutines.Dispatchers

class PhraseViewModel (private val repository: PhraseRepository) : ViewModel(){

    suspend fun fetchGetAllPhrases() = liveData(viewModelScope.coroutineContext + Dispatchers.Main) {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(repository.getAllPhrases()))
        } catch (e:Exception) {
            emit(Resource.Failure(e))
        }
    }

    suspend fun savePhrase(phrase: Phrase) {
        repository.savePhrase(phrase)
    }

}

class PhraseViewModelFactory(private val repo: PhraseRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(PhraseViewModel::class.java).newInstance(repo)
    }
}