package com.nikitayasiulevich.pexelsapp.presentation.details

sealed class DetailsScreenState {

    data object Initial : DetailsScreenState()

    data class Photo(val photo: com.nikitayasiulevich.pexelsapp.domain.Photo): DetailsScreenState()
}