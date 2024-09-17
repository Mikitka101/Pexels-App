package com.nikitayasiulevich.pexelsapp.presentation.bookmarks

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.nikitayasiulevich.pexelsapp.data.repository.PhotosRepository
import com.nikitayasiulevich.pexelsapp.domain.Photo

class BookmarksViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = PhotosRepository(application)

    val allPhotos: LiveData<List<Photo>> = repository.likedPhotos
}