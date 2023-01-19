package com.phrasenote.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.phrasenote.R
import com.phrasenote.core.Resource
import com.phrasenote.data.local.AppDatabase
import com.phrasenote.data.local.PhraseLocalDataSource
import com.phrasenote.data.model.Phrase
import com.phrasenote.databinding.FragmentHomePageBinding
import com.phrasenote.presentation.PhraseViewModel
import com.phrasenote.presentation.PhraseViewModelFactory
import com.phrasenote.repository.PhraseRepositoryImpl
import com.phrasenote.ui.home.adapters.PhraseAdapter
import com.phrasenote.ui.home.adapters.ResourceAdapter


class HomePageFragment : Fragment(R.layout.fragment_home_page), PhraseAdapter.OnPhraseClickListener, ResourceAdapter.OnResourceClickListener {

    private lateinit var binding: FragmentHomePageBinding
    private lateinit var phraseAdapter: PhraseAdapter
    private lateinit var resourceAdapter: ResourceAdapter

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

        binding.button.setOnClickListener {
            val action = HomePageFragmentDirections.actionHomePageFragmentToAddPhraseFragment()
            findNavController().navigate(action)
        }

        viewModel.fetchGetAllPhrases().observe(viewLifecycleOwner, Observer { result ->

            when (result) {
                is Resource.Loading -> {
                    Log.d("HomePage", "Loading")
                }
                is Resource.Success -> {

                    phraseAdapter = PhraseAdapter(result.data.results, this@HomePageFragment)
                    binding.rvMyPhrases.apply {
                        adapter = phraseAdapter
                    }

                    Log.d("HomePage", "Success: ${result.data.results.size}")

                }
                is Resource.Failure -> {
                    Log.d("HomePage", "Failure")
                }
            }

        })

        viewModel.fetchGetAllResources().observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Loading -> {
                    Log.d("HomePage", "Loading")
                }
                is Resource.Success -> {

                    resourceAdapter = ResourceAdapter( result.data.results , this@HomePageFragment)
                    binding.rvResources.apply {
                        adapter = resourceAdapter
                    }

                    //Log.d("HomePage", "Success: ${result.data.results.size}")

                }
                is Resource.Failure -> {
                    Log.d("HomePage", "Failure")
                }
            }        })
    }

    override fun onPhraseClick(phrase: Phrase) {
        Log.d("HomePage", "PhraseClick: ${phrase.title}")
        val bundle = Bundle().apply {
            putSerializable("phrase", phrase)
        }
        viewModel.setCurrentPhrase(phrase)
        findNavController().navigate(R.id.action_homePageFragment_to_detailPhraseFragment, bundle)
    }

    override fun onResourceClick(resource: com.phrasenote.data.model.Resource) {
        TODO("Not yet implemented")
    }

}