package com.nikitayasiulevich.pexelsapp.presentation.details

import android.app.Application
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nikitayasiulevich.pexelsapp.domain.Photo
import com.nikitayasiulevich.pexelsapp.presentation.commons.PhotosListItem
import com.nikitayasiulevich.pexelsapp.ui.theme.LightGray
import com.nikitayasiulevich.pexelsapp.ui.theme.Red40
import com.nikitayasiulevich.pexelsapp.ui.theme.WhiteFF

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    photo: Photo,
    application: Application,
    onBackPressed: () -> Unit
) {
    val viewModel: DetailsViewModel = viewModel(
        factory = DetailsViewModelFactory(photo, application)
    )
    val screenState = viewModel.screenState.observeAsState(DetailsScreenState.Initial)
    val currentState = screenState.value

    if (currentState is DetailsScreenState.Photo) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 18.dp)
                    .fillMaxSize()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    FloatingActionButton(
                        modifier = Modifier
                            .size(50.dp),
                        shape = RoundedCornerShape(15.dp),
                        containerColor = LightGray,
                        onClick = {
                            onBackPressed()
                        }
                    ) {
                        Icon(
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxSize(),
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "back button"
                        )
                    }
                    Row(
                        modifier = Modifier
                            .padding(end = 50.dp)
                            .fillMaxWidth()
                            .height(56.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Text(
                            text = currentState.photo.photographer,
                            fontSize = 22.sp
                        )
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                PhotosListItem(
                    photo = currentState.photo,
                    onPhotoClickListener = {}
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    FloatingActionButton(
                        modifier = Modifier
                            .width(200.dp)
                            .height(56.dp),
                        shape = RoundedCornerShape(28.dp),
                        containerColor = LightGray,
                        onClick = {
                            viewModel.downloadImage(currentState.photo.src)
                        }
                    ) {
                        Row(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            FloatingActionButton(
                                modifier = Modifier
                                    .fillMaxHeight(),
                                shape = CircleShape,
                                containerColor = Red40,
                                onClick = {}
                            ) {
                                Icon(
                                    modifier = Modifier.background(Color.Transparent),
                                    imageVector = Icons.Default.KeyboardArrowDown,
                                    contentDescription = null,
                                    tint = WhiteFF
                                )
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(end = 25.dp),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Download",
                                    fontSize = 18.sp
                                )
                            }
                        }
                    }
                    FloatingActionButton(
                        shape = CircleShape,
                        containerColor = LightGray,
                        onClick = { }
                    ) {
                        Icon(imageVector = Icons.Outlined.FavoriteBorder, contentDescription = null)
                    }
                }
            }


        }
    }

}