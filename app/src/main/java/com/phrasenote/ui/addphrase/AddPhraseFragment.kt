package com.phrasenote.ui.addphrase

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.phrasenote.R
import com.phrasenote.databinding.FragmentAddPhraseBinding


class AddPhraseFragment : Fragment(R.layout.fragment_add_phrase) {

    private lateinit var binding: FragmentAddPhraseBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddPhraseBinding.bind(view)
    }
}