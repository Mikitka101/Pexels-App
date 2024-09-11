package com.nikitayasiulevich.pexelsapp.presentation.home

import com.nikitayasiulevich.pexelsapp.domain.Photo

sealed class HomeScreenState {

    data object Initial : HomeScreenState()

    data object Loading : HomeScreenState()

    data class Photos(
        val photos: List<Photo>,
        val nextDataIsLoading: Boolean = false
    ) : HomeScreenState()
}