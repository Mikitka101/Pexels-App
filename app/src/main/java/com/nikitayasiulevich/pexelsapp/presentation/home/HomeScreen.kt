package com.nikitayasiulevich.pexelsapp.presentation.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nikitayasiulevich.pexelsapp.domain.Photo
import com.nikitayasiulevich.pexelsapp.presentation.commons.PhotosListItem
import com.nikitayasiulevich.pexelsapp.ui.theme.Black
import com.nikitayasiulevich.pexelsapp.ui.theme.LightGray
import com.nikitayasiulevich.pexelsapp.ui.theme.Red40
import com.nikitayasiulevich.pexelsapp.ui.theme.WhiteFF

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
            .padding(bottom = 56.dp)
    ) { paddingValues ->
        when (currentState) {
            is HomeScreenState.Photos -> {
                Column(
                    Modifier
                        .padding(horizontal = 10.dp)
                        .fillMaxSize()
                ) {
                    Spacer(modifier = Modifier.height(8.dp))
                    SearchBar()
                    Spacer(modifier = Modifier.height(10.dp))

                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        itemsIndexed(
                            listOf(
                                "Ice",
                                "Watches",
                                "Drawing",
                                "Castle",
                                "Nature",
                                "Lego",
                                "Apple"
                            ),
                            key = { _, it -> it }
                        ) { index, it ->
                            Button(
                                modifier = Modifier.padding(horizontal = 5.dp),
                                colors = ButtonDefaults.buttonColors()
                                    .copy(
                                        containerColor = if (index == 0)
                                            Red40
                                        else
                                            LightGray
                                    ),
                                onClick = { /*TODO*/ }
                            ) {
                                Text(
                                    text = it,
                                    color = if (index == 0)
                                        WhiteFF
                                    else
                                        Black
                                )
                            }

                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Photos(
                        paddingValues = paddingValues,
                        viewModel = viewModel,
                        photos = currentState.photos,
                        onPhotoClickListener = { photo ->
                            onPhotoClickListener(photo)
                        },
                        nextDataIsLoading = currentState.nextDataIsLoading
                    )
                }
            }

            HomeScreenState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = Color.DarkGray)
                }
            }

            HomeScreenState.Initial -> {

            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar() {
    var text by rememberSaveable {
        mutableStateOf("")
    }
    var active by rememberSaveable {
        mutableStateOf(false)
    }
    SearchBar(
        modifier = Modifier
            .fillMaxWidth(),
        query = text,
        onQueryChange = { text = it },
        onSearch = { active = false },
        active = active,
        onActiveChange = {
            active = it
        },
        placeholder = { Text(text = "Search") },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search icon"
            )
        },
        trailingIcon = {
            if (active && text.isNotEmpty()) {
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = "Clear icon",
                    modifier = Modifier.clickable {
                        text = ""
                    }
                )
            }
        }
    ) {

    }
}

@Composable
fun Photos(
    paddingValues: PaddingValues,
    viewModel: HomeViewModel,
    photos: List<Photo>,
    onPhotoClickListener: (Photo) -> Unit,
    nextDataIsLoading: Boolean,
) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        verticalItemSpacing = 16.dp,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(
            count = photos.size,
            key = { index ->
                val photo = photos[index]
                "${photo.id}${index}"
            }
        ) {
            PhotosListItem(photo = photos[it]) { photo ->
                onPhotoClickListener(photo)
            }
        }
        item {
            if (nextDataIsLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = Color.DarkGray)
                }
            } else {
                SideEffect {
                    viewModel.loadNextCuratedPhotos()
                }
            }
        }
    }
}