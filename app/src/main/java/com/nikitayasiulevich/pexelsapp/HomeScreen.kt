package com.nikitayasiulevich.pexelsapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nikitayasiulevich.pexelsapp.domain.Photo
import com.nikitayasiulevich.pexelsapp.ui.theme.Red40

@Composable
fun HomeScreen(
    paddingValues: PaddingValues,
    onPhotoClickListener: (Photo) -> Unit
) {
    val viewModel: HomeViewModel = viewModel()
    val screenState = viewModel.screenState.observeAsState(HomeScreenState.Initial)

    val currentState = screenState.value

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 56.dp),
        bottomBar = {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(3.dp)
                    .background(color = Red40)
            )
        }
    ) { paddingValues ->
        when (currentState) {
            is HomeScreenState.Photos -> {
                Photos(
                    paddingValues = paddingValues,
                    photos = currentState.photos,
                    onPhotoClickListener = { photo ->
                        onPhotoClickListener(photo)
                    }
                )
            }

            HomeScreenState.Initial -> {

            }
        }
    }
}

@Composable
fun Photos(
    paddingValues: PaddingValues,
    photos: List<Photo>,
    onPhotoClickListener: (Photo) -> Unit
) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        verticalItemSpacing = 4.dp,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier.fillMaxSize()
    ){
        items(
            items = photos,
            key = { it.id }
        ) {
            PhotosListItem(photo = it) { photo ->
                onPhotoClickListener(photo)
            }
        }
    }
}