package com.nikitayasiulevich.pexelsapp.presentation.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nikitayasiulevich.pexelsapp.data.repository.PhotosRepository
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val initialState = HomeScreenState.Initial

    private val _screenState = MutableLiveData<HomeScreenState>(initialState)
    val screenState: LiveData<HomeScreenState> = _screenState

    private val repository = PhotosRepository(application)

    init {
        _screenState.value = HomeScreenState.Loading
        loadCuratedPhotos()
    }

    private fun loadCuratedPhotos() {
        viewModelScope.launch {
            val photos = repository.loadCuratedPhotos()
            val titles = repository.loadFeaturedCollections()
            _screenState.value = HomeScreenState.Photos(photos = photos, titles = titles)
        }
    }

    fun loadNextCuratedPhotos() {
        _screenState.value = HomeScreenState.Photos(
            photos = repository.photos,
            titles = repository.collections,
            nextDataIsLoading = true
        )
        loadCuratedPhotos()
    }
}