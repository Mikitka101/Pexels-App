package com.nikitayasiulevich.pexelsapp.presentation.details

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nikitayasiulevich.pexelsapp.data.repository.PhotosRepository
import com.nikitayasiulevich.pexelsapp.domain.Photo
import kotlinx.coroutines.launch

class DetailsViewModel(
    photo: Photo,
    application: Application
) : AndroidViewModel(application) {

    private val _screenState = MutableLiveData<DetailsScreenState>(DetailsScreenState.Initial)
    val screenState: LiveData<DetailsScreenState> = _screenState

    private val repository = PhotosRepository(application)

    init {
        loadDetails(photo)
    }

    fun loadDetails(photo: Photo) {
        _screenState.value = DetailsScreenState.Photo(
            photo = photo
        )
    }

    fun downloadImage(url: String) {
        repository.downloadFile(url)
    }

    fun changeLikedState(photo: Photo) {
        viewModelScope.launch {
            if (repository.getPhoto(photo.id) == null) {
                repository.addPhotoToLiked(photo)
            } else {
                repository.deletePhotoFromLiked(photo.id)
            }
        }
    }
}