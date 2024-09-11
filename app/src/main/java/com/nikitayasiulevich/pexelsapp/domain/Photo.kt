package com.nikitayasiulevich.pexelsapp.domain

data class Photo(
    val id: String,
    val height: Int,
    val width: Int,
    val url: String,
    val alt: String,
    val avgColor: String,
    val photographer: String,
    val src: String
)