package com.nikitayasiulevich.pexelsapp.presentation.bookmarks

import com.nikitayasiulevich.pexelsapp.domain.Photo

sealed class BookmarksScreenState {

    data object Initial : BookmarksScreenState()

    data object Loading : BookmarksScreenState()

    data class Photos(
        val photos: List<Photo>
    ) : BookmarksScreenState()
}