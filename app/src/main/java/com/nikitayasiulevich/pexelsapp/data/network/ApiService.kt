package com.nikitayasiulevich.pexelsapp.data.network

import com.nikitayasiulevich.pexelsapp.data.model.CollectionsListDto
import com.nikitayasiulevich.pexelsapp.data.model.PhotosListDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {

    @GET("curated")
    suspend fun loadCuratedPhotos(
        @Header("Authorization") key: String,
        @Query("per_page") perPage: Int = 30,
        @Query("page") page: Int? = null
    ): PhotosListDto

    @GET("collections/featured")
    suspend fun loadFeaturedCollections(
        @Header("Authorization") key: String,
        @Query("per_page") perPage: Int = 10,
        @Query("page") page: Int? = null
    ): CollectionsListDto
}