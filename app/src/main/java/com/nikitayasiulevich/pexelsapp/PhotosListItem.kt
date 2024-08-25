package com.nikitayasiulevich.pexelsapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.nikitayasiulevich.pexelsapp.domain.Photo

@Composable
fun PhotosListItem(
    photo: Photo,
    onPhotoClickListener: (Photo) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(photo.height.dp)
            .padding(8.dp)
            .clickable { onPhotoClickListener(photo) },
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors().copy(containerColor = Color.DarkGray)
    ){

    }
}