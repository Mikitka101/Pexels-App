package com.nikitayasiulevich.pexelsapp.presentation.details

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nikitayasiulevich.pexelsapp.domain.Photo

class DetailsViewModelFactory(
    private val photo: Photo,
    private val application: Application
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailsViewModel(photo, application) as T
    }
}