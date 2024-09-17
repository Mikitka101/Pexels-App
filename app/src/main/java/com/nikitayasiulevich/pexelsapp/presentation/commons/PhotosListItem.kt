package com.nikitayasiulevich.pexelsapp.presentation.commons

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.nikitayasiulevich.pexelsapp.domain.Photo

@Composable
fun PhotosListItem(
    shape: Shape = RoundedCornerShape(25.dp),
    photo: Photo,
    onPhotoClickListener: (Photo) -> Unit,
    onPhotoDragStart: (Photo) -> Unit,
    onPhotoDragEnd: () -> Unit,
) {
    val aspectRatio: Float by remember {
        derivedStateOf { (photo.width.toFloat()) / (photo.height.toFloat()) }
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(aspectRatio)
            .clickable { onPhotoClickListener(photo) }
            .pointerInput(Unit) {
                detectDragGesturesAfterLongPress(
                    onDragStart = { onPhotoDragStart(photo) },
                    onDragCancel = { onPhotoDragEnd() },
                    onDragEnd = { onPhotoDragEnd() },
                    onDrag = { _, _ -> }
                )
            }
        ,
        shape = shape,
        colors = CardDefaults.cardColors().copy(containerColor = Color.DarkGray)
    ) {
        val showShimmer = remember { mutableStateOf(true) }
        AsyncImage(
            model = photo.srcLarge,
            modifier = Modifier
                .background(shimmerBrush(targetValue = 1300f, showShimmer = showShimmer.value, color = photo.avgColor))
                .fillMaxSize(),
            contentDescription = photo.alt,
            contentScale = ContentScale.Crop,
            onSuccess = {
                showShimmer.value = false
            }
        )
    }
}

@Composable
fun shimmerBrush(showShimmer: Boolean = true, targetValue: Float = 1000f, color: Color): Brush {
    return if (showShimmer) {
        val shimmerColors = listOf(
            color.copy(alpha = 0.99f),
            color.copy(alpha = 0.6f),
            color.copy(alpha = 0.99f),
        )

        val transition = rememberInfiniteTransition(label = "")
        val translateAnimation = transition.animateFloat(
            initialValue = 0f,
            targetValue = targetValue,
            animationSpec = infiniteRepeatable(
                animation = tween(800), repeatMode = RepeatMode.Reverse
            ), label = ""
        )
        Brush.linearGradient(
            colors = shimmerColors,
            start = Offset.Zero,
            end = Offset(x = translateAnimation.value, y = translateAnimation.value)
        )
    } else {
        Brush.linearGradient(
            colors = listOf(Color.Transparent, Color.Transparent),
            start = Offset.Zero,
            end = Offset.Zero
        )
    }
}