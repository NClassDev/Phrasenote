package com.phrasenote.ui.detailphrase

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.phrasenote.R
import com.phrasenote.data.local.AppDatabase
import com.phrasenote.data.local.PhraseLocalDataSource
import com.phrasenote.databinding.FragmentPhraseDetailBinding
import com.phrasenote.presentation.PhraseViewModel
import com.phrasenote.presentation.PhraseViewModelFactory
import com.phrasenote.repository.PhraseRepositoryImpl
import com.phrasenote.ui.home.HomePageFragmentArgs


class DetailPhraseFragment : Fragment(R.layout.fragment_phrase_detail) {

    private lateinit var binding: FragmentPhraseDetailBinding

    val args: HomePageFragmentArgs by navArgs()

    private val viewModel by viewModels<PhraseViewModel> {
        PhraseViewModelFactory(
            PhraseRepositoryImpl(
                PhraseLocalDataSource(AppDatabase.getDatabase(requireContext()).phraseDao())
            )
        )
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPhraseDetailBinding.bind(view)

        val phrase = args.phrase

        binding.apply {
            tvAuthor.text = phrase.author
            tvResource.text = phrase.resource
            tvLocationTitle.text = phrase.location
            tvPhrase.text = phrase.phrase
            tvPhraseExample.text = phrase.phrase_example
            tvMeaning.text = phrase.meaning
            tvCreatedAt.text = phrase.create_at
            // TODO Create a Interface to load a Image from Camera
            Glide.with(binding.root).load(R.drawable.img_default_1).transform(CircleCrop()).into(binding.imgResource)
        }

    }

}