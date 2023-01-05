package com.phrasenote

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.phrasenote.databinding.FragmentPhraseDetailBinding


class PhraseDetailFragment : Fragment(R.layout.fragment_phrase_detail) {

    private lateinit var binding: FragmentPhraseDetailBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPhraseDetailBinding.bind(view)
    }

}