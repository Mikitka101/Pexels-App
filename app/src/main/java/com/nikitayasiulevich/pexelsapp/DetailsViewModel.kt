package com.nikitayasiulevich.pexelsapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nikitayasiulevich.pexelsapp.domain.Photo

class DetailsViewModel(
    photo: Photo
) : ViewModel() {

    private val _screenState = MutableLiveData<DetailsScreenState>(DetailsScreenState.Initial)
    val screenState: LiveData<DetailsScreenState> = _screenState

    init {
        loadDetails(photo)
    }

    fun loadDetails(photo: Photo) {
        _screenState.value = DetailsScreenState.Photo(
            photo = photo
        )
    }
}