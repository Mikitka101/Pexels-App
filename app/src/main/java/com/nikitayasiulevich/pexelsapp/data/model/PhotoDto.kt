package com.nikitayasiulevich.pexelsapp.data.model

import com.google.gson.annotations.SerializedName

data class PhotoDto(
     @SerializedName("id") val id: String,
     @SerializedName("height") val height: String,
     @SerializedName("width") val width: String,
     @SerializedName("url") val url: String,
     @SerializedName("alt") val alt: String,
     @SerializedName("avg_color") val avgColor: String,
     @SerializedName("photographer") val photographer: String,
     @SerializedName("src") val src: SrcDto
)
