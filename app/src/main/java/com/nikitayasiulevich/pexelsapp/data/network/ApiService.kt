package com.nikitayasiulevich.pexelsapp.data.network

import com.nikitayasiulevich.pexelsapp.data.model.PhotosListDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {

    @GET("curated")
    suspend fun loadCurated(
        @Header("Authorization") key: String,
        @Query("per_page") perPage: Int = 10,
        @Query("page") page: Int? = null
    ): PhotosListDto
}