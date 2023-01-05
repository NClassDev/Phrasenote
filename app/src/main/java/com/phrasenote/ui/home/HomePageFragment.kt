package com.phrasenote.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.phrasenote.R
import com.phrasenote.databinding.FragmentHomePageBinding


class HomePageFragment : Fragment(R.layout.fragment_home_page) {

    private lateinit var binding: FragmentHomePageBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomePageBinding.bind(view)
    }

}