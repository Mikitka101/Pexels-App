package com.nikitayasiulevich.pexelsapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nikitayasiulevich.pexelsapp.domain.Photo
import kotlin.random.Random

class HomeViewModel : ViewModel() {

    private val sourceList = mutableListOf<Photo>().apply {
        repeat(30) {
            add(Photo(id = it, height = Random.nextInt(from = 150, until = 300)))
        }
    }
    private val initialState = HomeScreenState.Photos(photos = sourceList)

    private val _screenState = MutableLiveData<HomeScreenState>(initialState)
    val screenState: LiveData<HomeScreenState> = _screenState

}