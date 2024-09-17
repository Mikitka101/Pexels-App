package com.nikitayasiulevich.pexelsapp.presentation.bookmarks


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nikitayasiulevich.pexelsapp.domain.Photo
import com.nikitayasiulevich.pexelsapp.presentation.commons.PhotosListItem
import com.nikitayasiulevich.pexelsapp.presentation.commons.ZoomedImageCard

@Composable
fun BookmarksScreen(
    paddingValues: PaddingValues,
    onPhotoClickListener: (Photo) -> Unit
) {
    val viewModel: BookmarksViewModel = viewModel()

    var showPhotoPreview by remember { mutableStateOf(false) }
    var activePhoto by remember { mutableStateOf<Photo?>(null) }

    val photos = viewModel.allPhotos.observeAsState(initial = listOf())

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 56.dp)
    ) { paddingValues ->
        Column(
            Modifier
                .padding(horizontal = 10.dp)
                .fillMaxSize()
        ) {
            Spacer(
                modifier = Modifier
                    .height(25.dp)
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = "Bookmarks",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(
                modifier = Modifier
                    .height(25.dp)
            )
            Photos(
                paddingValues = paddingValues,
                viewModel = viewModel,
                photos = photos.value,
                onPhotoClickListener = { photo ->
                    onPhotoClickListener(photo)
                },
                onPhotoDragStart = { photo ->
                    activePhoto = photo
                    showPhotoPreview = true
                },
                onPhotoDragEnd = { showPhotoPreview = false }
            )

        }
        ZoomedImageCard(
            modifier = Modifier.padding(20.dp),
            isVisible = showPhotoPreview,
            photo = activePhoto
        )
        /*when (currentState) {
            is BookmarksScreenState.Photos -> {
                Column(
                    Modifier
                        .padding(horizontal = 10.dp)
                        .fillMaxSize()
                ) {
                    Spacer(modifier = Modifier.height(10.dp))
                    Photos(
                        paddingValues = paddingValues,
                        viewModel = viewModel,
                        photos = currentState.photos,
                        onPhotoClickListener = { photo ->
                            onPhotoClickListener(photo)
                        },
                        onPhotoDragStart = { photo ->
                            activePhoto = photo
                            showPhotoPreview = true
                        },
                        onPhotoDragEnd = { showPhotoPreview = false }
                    )
                }
                ZoomedImageCard(
                    modifier = Modifier.padding(20.dp),
                    isVisible = showPhotoPreview,
                    photo = activePhoto
                )
            }

            BookmarksScreenState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = Color.DarkGray)
                }
            }

            BookmarksScreenState.Initial -> {

            }
        }*/
    }
}

@Composable
fun Photos(
    paddingValues: PaddingValues,
    viewModel: BookmarksViewModel,
    photos: List<Photo>,
    onPhotoClickListener: (Photo) -> Unit,
    onPhotoDragStart: (Photo) -> Unit,
    onPhotoDragEnd: () -> Unit,
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
            PhotosListItem(
                photo = photos[it],
                onPhotoClickListener = { photo ->
                    onPhotoClickListener(photo)
                },
                onPhotoDragStart = { photo ->
                    onPhotoDragStart(photo)
                },
                onPhotoDragEnd = onPhotoDragEnd
            )
        }
    }
}