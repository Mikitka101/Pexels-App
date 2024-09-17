package com.nikitayasiulevich.pexelsapp.data.model

import com.google.gson.annotations.SerializedName

data class CollectionDto(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String
)
