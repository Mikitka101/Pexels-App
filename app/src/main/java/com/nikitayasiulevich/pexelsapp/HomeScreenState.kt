package com.nikitayasiulevich.pexelsapp

import com.nikitayasiulevich.pexelsapp.domain.Photo

sealed class HomeScreenState {

    data object Initial : HomeScreenState()

    data class Photos(val photos: List<Photo>): HomeScreenState()
}