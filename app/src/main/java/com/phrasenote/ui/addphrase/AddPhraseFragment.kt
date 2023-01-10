package com.phrasenote.ui.addphrase

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.phrasenote.R

import com.phrasenote.core.DataMapper
import com.phrasenote.core.Resource
import com.phrasenote.data.local.AppDatabase
import com.phrasenote.data.local.PhraseLocalDataSource
import com.phrasenote.data.model.Phrase
import com.phrasenote.databinding.FragmentAddPhraseBinding
import com.phrasenote.permission.Permission
import com.phrasenote.permission.PermissionManager
import com.phrasenote.presentation.PhraseViewModel
import com.phrasenote.presentation.PhraseViewModelFactory
import com.phrasenote.repository.PhraseRepositoryImpl


class AddPhraseFragment : Fragment(R.layout.fragment_add_phrase) {

    private lateinit var binding: FragmentAddPhraseBinding
    private var currentImagePath: String? = null
    private var isImagePhrase: Boolean = false
    private var imgPickerCode: String? = null

    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private val permissionManager = PermissionManager.from(this)

    private val viewModel by viewModels<PhraseViewModel> {
        PhraseViewModelFactory(PhraseRepositoryImpl(PhraseLocalDataSource(AppDatabase.getDatabase(requireContext()).phraseDao())))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddPhraseBinding.bind(view)
        binding.btnSave.setOnClickListener {
            savePhrase()
        }
        binding.imgBack.setOnClickListener {
            findNavController().navigate(R.id.action_addPhraseFragment_to_homePageFragment)
        }

        binding.imgCircleResource.setOnClickListener {
            activity?.let {
                if(checkPermission()) {
                    imgPickerCode = "CAMERA"
                    val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    resultLauncher.launch(cameraIntent)
                }
            }
        }

        resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK && imgPickerCode.equals("CAMERA")) {
                handleCameraImage(result.data)
            }

            // TODO From Gallery
//            if (result.resultCode == Activity.RESULT_OK && imgPickerCode.equals("GALLERY")) {
//                handleGaleryImage(result.data)
//            }

        }
    }

    private fun savePhrase() {

        val mPhrase = getDataFromIU()

        viewModel.savePhrase(mPhrase).observe(viewLifecycleOwner, Observer { result ->
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

    private fun getDataFromIU(): Phrase{
        binding.apply {
            return Phrase(
                title = edResourceTitle.text.toString() ,
                author = edAuthorResource.text.toString(),
                location = edLocation.text.toString(),
                phrase = edPhrase.text.toString(),
                phrase_example = edPhraseExample.text.toString(),
                meaning = edMeaning.text.toString(),
                create_at = DataMapper.getDate(),
                likes = 0,
                resource = edResourceTitle.text.toString(),
                resourceImg = currentImagePath!!
            )
        }
    }

    private fun handleCameraImage(data: Intent?) {
        val bitmap = data?.extras?.get("data") as Bitmap
        val bitmap1080x768 = Bitmap.createScaledBitmap(bitmap, 1024, 768, false )

        var imageUri = DataMapper.encodeImage(bitmap1080x768)
        currentImagePath = imageUri

        binding.imgCircleResource.setImageBitmap(DataMapper.decodeImage(currentImagePath!!))

        isImagePhrase = true
    }

    private fun checkPermission(): Boolean {
        var response = false

        permissionManager
            // Check all permissions without bundling them
            .request(Permission.Storage, Permission.Camera)
            //.rationale("We want permission for Storage (Read+Write) and  Camera")
            .checkDetailedPermission { result ->
                if (result.all { it.value }) {
                    response = true

                } else {
                    response = false
                    Snackbar.make(
                        binding.root,
                        "Permisos de Cámara y Almacenamiento son necesarios",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }

        return response
    }

}