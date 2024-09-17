package com.nikitayasiulevich.pexelsapp.domain

import androidx.compose.ui.graphics.Color

data class Photo(
    val id: String,
    val height: Int,
    val width: Int,
    val url: String,
    val alt: String,
    val avgColor: Color,
    val photographer: String,
    val srcOriginal: String,
    val srcLarge: String
)