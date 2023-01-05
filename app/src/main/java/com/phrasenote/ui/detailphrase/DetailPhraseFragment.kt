package com.phrasenote.ui.detailphrase

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.phrasenote.R
import com.phrasenote.databinding.FragmentPhraseDetailBinding


class DetailPhraseFragment : Fragment(R.layout.fragment_phrase_detail) {

    private lateinit var binding: FragmentPhraseDetailBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPhraseDetailBinding.bind(view)
    }

}