package com.nikitayasiulevich.pexelsapp.presentation.home

import com.nikitayasiulevich.pexelsapp.domain.FeaturedCollection
import com.nikitayasiulevich.pexelsapp.domain.Photo

sealed class HomeScreenState {

    data object Initial : HomeScreenState()

    data object Loading : HomeScreenState()

    data class Photos(
        val titles: List<FeaturedCollection>,
        val photos: List<Photo>,
        val nextDataIsLoading: Boolean = false
    ) : HomeScreenState()
}