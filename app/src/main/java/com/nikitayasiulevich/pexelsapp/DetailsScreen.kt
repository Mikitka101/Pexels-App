package com.nikitayasiulevich.pexelsapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nikitayasiulevich.pexelsapp.domain.Photo
import com.nikitayasiulevich.pexelsapp.ui.theme.Red40

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    photo: Photo,
    onBackPressed: () -> Unit
) {
    val viewModel: DetailsViewModel = viewModel(
        factory = DetailsViewModelFactory(photo)
    )
    val screenState = viewModel.screenState.observeAsState(DetailsScreenState.Initial)
    val currentState = screenState.value

    if(currentState is DetailsScreenState.Photo){
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 56.dp),
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = currentState.photo.photographer + "id: ${currentState.photo.id}"
                        )
                    },
                    navigationIcon = {
                        FloatingActionButton(
                            modifier = Modifier.padding(16.dp),
                            onClick = {
                                onBackPressed()
                            }
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "back button"
                            )
                        }
                    }
                )
            },
            bottomBar = {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(3.dp)
                        .background(color = Red40)
                )
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.photo_placeholder),
                    contentDescription = currentState.photo.alt,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(30.dp)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){
                   FloatingActionButton(onClick = { }) {
                       Text(
                           text = "Download"
                       )
                   }
                    FloatingActionButton(onClick = { }) {
                        Icon(imageVector = Icons.Outlined.FavoriteBorder, contentDescription = null)
                    }
                }
            }


        }
    }

}