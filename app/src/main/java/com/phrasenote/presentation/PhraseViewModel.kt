package com.phrasenote.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.phrasenote.core.Resource
import com.phrasenote.data.model.Phrase
import com.phrasenote.repository.PhraseRepository
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.coroutineContext

class PhraseViewModel (private val repository: PhraseRepository) : ViewModel(){

    val currentPhrase = MutableLiveData<Phrase>()
    val currentResource = MutableLiveData<com.phrasenote.data.model.Resource>()

    fun fetchGetAllPhrases() = liveData(viewModelScope.coroutineContext + Dispatchers.Main) {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(repository.getAllPhrases()))
        } catch (e:Exception) {
            emit(Resource.Failure(e))
        }
    }

    fun fetchGetAllResources() = liveData(viewModelScope.coroutineContext + Dispatchers.Main) {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(repository.getAllResources()))
        } catch (e:Exception) {
            emit(Resource.Failure(e))
        }
    }

     fun savePhrase(phrase: Phrase) = liveData(viewModelScope.coroutineContext + Dispatchers.Main) {
         emit(Resource.Loading())
         try {
             emit(Resource.Success(repository.savePhrase(phrase)))
         } catch (e:Exception) {
             emit(Resource.Failure(e))
             Log.d("Page", Resource.Failure(e).toString())
         }
    }

    fun saveResource(resource: com.phrasenote.data.model.Resource) = liveData (viewModelScope.coroutineContext + Dispatchers.Main) {
        emit(Resource.Loading())
        try{
            emit(Resource.Success(repository.saveResource(resource)))
        }catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }

    fun getResourceByTitle(title: String) = liveData(viewModelScope.coroutineContext+Dispatchers.Main) {
        emit(Resource.Loading())
        try {
           emit(Resource.Success(repository.getResourceByTitle(title)))
        } catch (e:Exception) {
            emit(Resource.Failure(e))
        }
    }

    fun setCurrentPhrase(phrase: Phrase) {
        currentPhrase.postValue(phrase)
    }

    fun setCurrentResource(resource: com.phrasenote.data.model.Resource) {
        currentResource.postValue(resource)
    }

}

class PhraseViewModelFactory(private val repo: PhraseRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(PhraseRepository::class.java).newInstance(repo)
    }
}