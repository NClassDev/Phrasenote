package com.phrasenote.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import com.phrasenote.R
import com.phrasenote.data.local.AppDatabase
import com.phrasenote.data.local.PhraseLocalDataSource
import com.phrasenote.databinding.FragmentHomePageBinding
import com.phrasenote.presentation.PhraseViewModel
import com.phrasenote.presentation.PhraseViewModelFactory
import com.phrasenote.repository.PhraseRepositoryImpl


class HomePageFragment : Fragment(R.layout.fragment_home_page) {

    private lateinit var binding: FragmentHomePageBinding

    private val viewModel by viewModels<PhraseViewModel> {
        PhraseViewModelFactory(
            PhraseRepositoryImpl(
                PhraseLocalDataSource(AppDatabase.getDatabase(requireContext()).phraseDao())
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomePageBinding.bind(view)
    }

}