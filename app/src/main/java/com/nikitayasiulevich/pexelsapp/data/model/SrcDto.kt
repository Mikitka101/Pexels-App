package com.nikitayasiulevich.pexelsapp.data.model

import com.google.gson.annotations.SerializedName

data class SrcDto(
    @SerializedName("original") val original: String,
    @SerializedName("large") val large: String
)
