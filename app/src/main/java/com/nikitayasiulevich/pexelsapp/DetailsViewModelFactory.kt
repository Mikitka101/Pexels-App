package com.nikitayasiulevich.pexelsapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nikitayasiulevich.pexelsapp.domain.Photo

class DetailsViewModelFactory(
    private val photo: Photo
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailsViewModel(photo) as T
    }
}