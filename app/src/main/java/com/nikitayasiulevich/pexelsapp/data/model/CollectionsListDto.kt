package com.nikitayasiulevich.pexelsapp.data.model

import com.google.gson.annotations.SerializedName

data class CollectionsListDto(
    @SerializedName("collections") val collections: List<CollectionDto>,
    @SerializedName("next_page") val nextPage: String?
)