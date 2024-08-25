package com.nikitayasiulevich.pexelsapp.domain

import androidx.compose.ui.unit.Dp

data class Photo(
    val id: Int = 0,
    val height: Int = 300,
    val width: Int = 400,
    val url: String = "https://img.freepik.com/free-vector/illustration-gallery-icon_53876-27002.jpg",
    val alt: String = "",
    val avgColor: String = "#FF235234",
    val photographer: String = "Name Surname"
)