package com.phrasenote.ui.addphrase

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.phrasenote.R
import com.phrasenote.core.Resource
import com.phrasenote.data.local.AppDatabase
import com.phrasenote.data.local.PhraseLocalDataSource
import com.phrasenote.data.model.Phrase
import com.phrasenote.data.model.PhraseDummie1
import com.phrasenote.data.model.toPhraseEntity
import com.phrasenote.databinding.FragmentAddPhraseBinding
import com.phrasenote.presentation.PhraseViewModel
import com.phrasenote.presentation.PhraseViewModelFactory
import com.phrasenote.repository.PhraseRepositoryImpl
import com.phrasenote.ui.home.adapters.PhraseAdapter


class AddPhraseFragment : Fragment(R.layout.fragment_add_phrase) {

    private lateinit var binding: FragmentAddPhraseBinding

    private val viewModel by viewModels<PhraseViewModel> {
        PhraseViewModelFactory(PhraseRepositoryImpl(PhraseLocalDataSource(AppDatabase.getDatabase(requireContext()).phraseDao())))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddPhraseBinding.bind(view)
        binding.btnSave.setOnClickListener {
            savePhrase()
        }
    }

    private fun savePhrase() {
        val tempPhrase = Phrase(title = "We are the ants")
        viewModel.savePhrase(tempPhrase).observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Loading -> {
                    Log.d("AdPhrasePage",  "Loading")
                }
                is Resource.Success -> {

                    Log.d("AdPhrasePage", "Success: Saved ")
                }
                is Resource.Failure -> {
                    Log.d("AdPhrasePage",  "Failure")
                }
            }
        })
    }

}