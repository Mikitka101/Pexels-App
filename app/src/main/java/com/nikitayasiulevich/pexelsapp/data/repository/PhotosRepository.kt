package com.nikitayasiulevich.pexelsapp.data.repository

import android.app.Application
import android.net.Uri
import android.util.Log
import com.nikitayasiulevich.pexelsapp.data.utils.Constants.API_KEY
import com.nikitayasiulevich.pexelsapp.data.mappers.PhotosMapper
import com.nikitayasiulevich.pexelsapp.data.network.AndroidDownloader
import com.nikitayasiulevich.pexelsapp.data.network.ApiFactory
import com.nikitayasiulevich.pexelsapp.domain.Photo

class PhotosRepository(
    application: Application
) {

    private val mapper = PhotosMapper()
    private val apiService = ApiFactory.apiService
    private val downloader = AndroidDownloader(application)

    private var nextPage: String? = null

    private val _photos = mutableListOf<Photo>()
    val photos: List<Photo>
        get() = _photos.toList()

    suspend fun loadCuratedPhotos(): List<Photo> {
        val startFrom = nextPage

        if (startFrom == null && photos.isNotEmpty()) return photos

        val response = if (startFrom == null) {
            apiService.loadCurated(API_KEY)
        } else {
            val uri = Uri.parse(startFrom)
            val nextPageNumber = uri.getQueryParameter("page")?.toInt()
            Log.d("PhotosRepository", nextPageNumber.toString())
            apiService.loadCurated(
                key = API_KEY,
                page = nextPageNumber
            )
        }
        nextPage = response.nextPage
        _photos.addAll(mapper.mapPhotosListDtoToPhotos(response))
        return photos
    }

    fun downloadFile(url: String) {
        downloader.downloadFile(url)
    }
}