package com.nikitayasiulevich.pexelsapp.data.model

import com.google.gson.annotations.SerializedName

data class PhotosListDto(
    @SerializedName("photos") val photos: List<PhotoDto>,
    @SerializedName("next_page") val nextPage: String?
)
